package digytal.desktop.app.form.modulo.acesso;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.form.FrmPrincipal;
import digytal.desktop.app.form.modulo.acesso.empresa.FrmLocalizaEmpresa;
import digytal.desktop.app.model.modulo.acesso.CredenciamentoResponse;
import digytal.desktop.app.service.publico.PublicService;
import digytal.desktop.components.Imagens;
import digytal.desktop.components.desktop.Formulario;
import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSCabecalho;
import digytal.desktop.components.desktop.ss.SSCaixaCombinacao;
import digytal.desktop.components.desktop.ss.SSCampoSenha;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.SSRodape;
import digytal.desktop.components.model.Empresa;
import digytal.desktop.components.model.Login;
import digytal.desktop.components.model.Sessao;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Component
public class FrmLogin extends JFrame {
	private static final Logger LOGGER = LogManager.getLogger(FrmLogin.class);
	private JPanel painel = new JPanel();
	private SSBotao btOk = new SSBotao();
	private SSBotao btSair = new SSBotao();
	private SSCampoTexto cLogin = new SSCampoTexto();
	private SSCampoSenha cSenha = new SSCampoSenha();
	private SSCaixaCombinacao cEmpresas = new SSCaixaCombinacao();
	private SSCabecalho cabecalho = new SSCabecalho();
	private SSRodape rodape = new SSRodape();
	private JPanel conteudo = new JPanel();
	private JLabel imagem = new JLabel();
	private JLabel cPrimeiroAcesso = new JLabel();

	@Autowired
	private PublicService service;	
	
	private int tentativas = 0;

	public FrmLogin() {
		painel.setLayout(new BorderLayout(0, 0));
		
		painel.add(cabecalho, BorderLayout.NORTH);
		painel.add(conteudo, BorderLayout.CENTER);
		painel.add(rodape, BorderLayout.SOUTH);
		cPrimeiroAcesso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iniciarCadastro();
			}			
		});
		
		cPrimeiroAcesso.setText("Primeiro Acesso?");
		cPrimeiroAcesso.setForeground(Color.BLUE.darker());
		cPrimeiroAcesso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		GridBagLayout gbl_conteudo = new GridBagLayout();
		conteudo.setLayout(gbl_conteudo);
		
		GridBagConstraints gbc_cLogin = new GridBagConstraints();
		gbc_cLogin.weightx = 1.0;
		gbc_cLogin.anchor = GridBagConstraints.NORTHWEST;
		gbc_cLogin.insets = new Insets(17, 5, 0, 5);
		gbc_cLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_cLogin.gridx = 1;
		gbc_cLogin.gridy = 0;
		conteudo.add(cLogin, gbc_cLogin);

		GridBagConstraints gbc_cSenha = new GridBagConstraints();
		gbc_cSenha.weightx = 1.0;
		gbc_cSenha.anchor = GridBagConstraints.NORTHEAST;
		gbc_cSenha.insets = new Insets(7, 5, 0, 5);
		gbc_cSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_cSenha.gridx = 1;
		gbc_cSenha.gridy = 1;
		conteudo.add(cSenha, gbc_cSenha);

		GridBagConstraints gbc_cEmpresa = new GridBagConstraints();
		gbc_cEmpresa.weightx = 1.0;
		gbc_cEmpresa.anchor = GridBagConstraints.NORTHEAST;
		gbc_cEmpresa.insets = new Insets(7, 5, 2, 5);
		gbc_cEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_cEmpresa.gridx = 1;
		gbc_cEmpresa.gridy = 2;
		conteudo.add(cEmpresas, gbc_cEmpresa);
		
		GridBagConstraints gbc_cNovaEmpresa = new GridBagConstraints();
		gbc_cNovaEmpresa.weighty = 1.0;
		gbc_cNovaEmpresa.anchor = GridBagConstraints.NORTHEAST;
		gbc_cNovaEmpresa.insets = new Insets(10, 5, 2, 5);
		gbc_cNovaEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNovaEmpresa.gridx = 1;
		gbc_cNovaEmpresa.gridy = 3;
		conteudo.add(cPrimeiroAcesso, gbc_cNovaEmpresa);
		
		GridBagConstraints gbc_cImagem = new GridBagConstraints();
		gbc_cImagem.gridheight = 4;
		gbc_cImagem.weighty = 1.0;
		gbc_cImagem.anchor = GridBagConstraints.NORTHEAST;
		gbc_cImagem.insets = new Insets(10, 2, 2, 0);
		gbc_cImagem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cImagem.gridx = 0;
		gbc_cImagem.gridy = 0;
		conteudo.add(imagem, gbc_cImagem);

		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});

		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		rodape.add(btOk);
		rodape.add(btSair);
		setContentPane(painel);
		setSize(new Dimension(368, 308));
		definir();

	}

	private void definir() {
		setTitle("LOGIN");
		cEmpresas.setRotulo("Empresa");

		btOk.setText("LOGAR");
		btOk.setIcone("key");

		cEmpresas.setVisible(false);
		cEmpresas.setEnabled(false);
		cEmpresas.setPrimeiroElementoVazio(true);

		cLogin.setTudoMaiusculo(false);
		cSenha.setTudoMaiusculo(false);
		cLogin.setText("");
		cSenha.setText("");		
		cLogin.setColunas(10);
		
		cLogin.setRotulo("Usuário");
		cSenha.setRotulo("Senha");
		
		cabecalho.setTitulo("LOGIN");
		cabecalho.setDescricao("Acesse o sistema");

		
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		conteudo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		rodape.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		btSair.setText("SAIR");
		btSair.setIcone("out");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		imagem.setIcon(Imagens.png("app/app-img-128"));
		setIconImage(Imagens.png("app/app-img-32").getImage());

		cLogin.setText("00000000000");
		cSenha.setText("asd123");

	}

	private void login() {
		try {
			Login login = new Login();
			login.setUsuario(getLogin());
			login.setSenha(getSenha());
			Response<Sessao> response = service.logar(login);
			Sessao sessao = Context.getSessao();
			BeanUtils.copyProperties(response.getBody(), sessao );
			if(sessao.getUsuario().getEmpresas()==null || sessao.getUsuario().getEmpresas().size()==0) {
				SSMensagem.avisa("Não existe empresa vinculada a este usuário");
				return;
			}
			Empresa empresa = null;
			if(sessao.getUsuario().isConsultor() || sessao.getUsuario().getEmpresas().size() > 1) {
				FrmLocalizaEmpresa frm = Context.getBean(FrmLocalizaEmpresa.class);
				frm.exibirEmpresas(sessao.getUsuario().getEmpresas());
				empresa = (Empresa) Formulario.dialogo(frm);
				if(empresa==null)
					return;
				else {
					Response res = service.selecionarEmpresa(empresa.getId());
					sessao.setToken(res.getBody().toString());
					System.out.println(sessao.getToken());
				}
				
			}else
				empresa = sessao.getUsuario().getEmpresas().get(0);
			
			sessao.setEmpresa(empresa);
			if (response.getStatus().isSuccess()) {
				FrmPrincipal frm = Context.getBean(FrmPrincipal.class);
				frm.iniciar(sessao);
				frm.exibirFormularioInicial();
				this.dispose();
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
			if(++tentativas == 2) {
				if(SSMensagem.pergunta("Deseja solicitar uma nova senha?")) {
					String vLogin = getLogin();
					if(vLogin==null || vLogin.isEmpty()) {
						SSMensagem.avisa("Informe o seu login");
						return;
					}
					
					Response<CredenciamentoResponse> resp = service.solicitarNovaSenha(getLogin());
					FrmNovaSenha frm = Context.getBean(FrmNovaSenha.class);
					frm.exibir(resp.getBody());
					this.dispose();
				}
			}
		} catch (Exception e) {
			SSMensagem.erro(e.getMessage());
			LOGGER.error(e.getMessage(),e);
		}
	}

	public void exibir() {
		this.setVisible(true);

	}

	public String getLogin() {
		return cLogin.getText();
	}

	public String getSenha() {
		return cSenha.getText();
	}
	
	private void iniciarCadastro() {
		FrmPrimeiroAcesso frm = Context.getBean(FrmPrimeiroAcesso.class);
		frm.setVisible(true);
	}
	
	private void fechar() {
		System.exit(0);
	}
}
