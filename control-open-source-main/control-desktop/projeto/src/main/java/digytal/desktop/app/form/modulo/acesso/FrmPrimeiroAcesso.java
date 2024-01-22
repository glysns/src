package digytal.desktop.app.form.modulo.acesso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
import digytal.desktop.app.model.modulo.acesso.CredenciamentoResponse;
import digytal.desktop.app.model.modulo.comum.cadastramento.CadastroSimplificadoRequest;
import digytal.desktop.app.service.publico.PublicService;
import digytal.desktop.components.Imagens;
import digytal.desktop.components.desktop.MDI;
import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSCabecalho;
import digytal.desktop.components.desktop.ss.SSCampoSenha;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.SSRodape;
import digytal.desktop.components.desktop.ss.util.SSFormatador;
import digytal.desktop.components.desktop.ss.util.SSTexto;
import digytal.desktop.components.desktop.ss.util.SSValidacao;
import digytal.desktop.components.model.Sessao;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

@Component
public class FrmPrimeiroAcesso extends JFrame {
	private static final Logger LOGGER = LogManager.getLogger(FrmPrimeiroAcesso.class);
	private JPanel painel = new JPanel();
	private SSBotao btOk = new SSBotao();
	private SSBotao btSair = new SSBotao();
	private SSCampoTexto cNomeFantasia = new SSCampoTexto();
	private SSCampoTexto cSobrenomeSocial = new SSCampoTexto();
	private SSCampoTexto cEmail = new SSCampoTexto();
	private SSCampoTexto cCpfCnpj = new SSCampoTexto();
	private SSCabecalho cabecalho = new SSCabecalho();
	private SSRodape rodape = new SSRodape();
	private JPanel conteudo = new JPanel();
	

	@Autowired
	private PublicService service;
	
	private CredenciamentoResponse credencial;
	public FrmPrimeiroAcesso() {
		

		painel.setLayout(new BorderLayout(0, 0));
		
		painel.add(cabecalho, BorderLayout.NORTH);
		painel.add(conteudo, BorderLayout.CENTER);
		painel.add(rodape, BorderLayout.SOUTH);
		

		GridBagLayout gbl_conteudo = new GridBagLayout();
		conteudo.setLayout(gbl_conteudo);
		
		GridBagConstraints gbc_cEmail = new GridBagConstraints();
		gbc_cEmail.weighty = 1.0;
		gbc_cEmail.weightx = 1.0;
		gbc_cEmail.anchor = GridBagConstraints.NORTHWEST;
		gbc_cEmail.insets = new Insets(5, 5, 0, 5);
		gbc_cEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_cEmail.gridx = 0;
		gbc_cEmail.gridy = 3;
		conteudo.add(cEmail, gbc_cEmail);
		
		GridBagConstraints gbc_cNomeFantasia = new GridBagConstraints();
		gbc_cNomeFantasia.weightx = 1.0;
		gbc_cNomeFantasia.anchor = GridBagConstraints.NORTHEAST;
		gbc_cNomeFantasia.insets = new Insets(5, 5, 5, 5);
		gbc_cNomeFantasia.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNomeFantasia.gridx = 0;
		gbc_cNomeFantasia.gridy = 1;
		conteudo.add(cNomeFantasia, gbc_cNomeFantasia);

		GridBagConstraints gbc_cSobrenomeSocial = new GridBagConstraints();
		gbc_cSobrenomeSocial.weightx = 1.0;
		gbc_cSobrenomeSocial.anchor = GridBagConstraints.NORTHEAST;
		gbc_cSobrenomeSocial.insets = new Insets(5, 5, 5, 5);
		gbc_cSobrenomeSocial.fill = GridBagConstraints.HORIZONTAL;
		gbc_cSobrenomeSocial.gridx = 0;
		gbc_cSobrenomeSocial.gridy = 2;
		conteudo.add(cSobrenomeSocial, gbc_cSobrenomeSocial);

		GridBagConstraints gbc_cCpfCnpj = new GridBagConstraints();
		gbc_cCpfCnpj.weightx = 1.0;
		gbc_cCpfCnpj.anchor = GridBagConstraints.NORTHEAST;
		gbc_cCpfCnpj.insets = new Insets(5, 5, 0, 5);
		gbc_cCpfCnpj.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCpfCnpj.gridx = 0;
		gbc_cCpfCnpj.gridy = 0;
		conteudo.add(cCpfCnpj, gbc_cCpfCnpj);
		
	
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});

		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});
		
		rodape.add(btOk);
		rodape.add(btSair);
		setContentPane(painel);
		definir();

	}

	private void definir() {
		setTitle("Digytal Control");
		cCpfCnpj.setRotulo("CPF \\ CNPJ");

		btOk.setText("CONFIRMAR");
		btOk.setIcone("ok");
		
		cEmail.setTudoMinusculo(true);
		cEmail.setText("");
		cSobrenomeSocial.setText("");		
		cEmail.setColunas(10);
		
		cEmail.setRotulo("E-mail");
		cSobrenomeSocial.setRotulo("Razão Social (sobrenome)");
		
		cabecalho.setTitulo("Primeiro Acesso");
		cabecalho.setDescricao("Informe os seus dados básicos");

		
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		conteudo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		rodape.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		btSair.setText("SAIR");
		btSair.setIcone("out");
		cNomeFantasia.setRotulo("Nome Fantasia (nome)");
		
		setSize(new Dimension(308, 340));
		setLocationRelativeTo(null);
		setIconImage(Imagens.png("app/app-img-32").getImage());

		cCpfCnpj.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				try {
					String cpfCnpj = cCpfCnpj.getText();
					if (cpfCnpj != null && cpfCnpj.trim().length() > 0)
						cCpfCnpj.setText(SSTexto.retiraSeparadores(cpfCnpj));
				} catch (Exception el) {
					el.printStackTrace();
				}
			}

			public void focusLost(FocusEvent e) {
				formataCpfCnpj(cCpfCnpj.getText());
			}
		});

	}
	private void formataCpfCnpj(String texto) {
		if (!SSValidacao.nuloOuVazio(texto)) {
			String cpfCnpj = SSTexto.retiraSeparadores(texto);
			cCpfCnpj.setText(SSFormatador.formatarCpfCnpj(cpfCnpj));
		}
	}

	private void confirmar() {
		try {
			if (SSMensagem.pergunta("Confirma concluir o cadastro?")) {
			
				CadastroSimplificadoRequest cadastro = new CadastroSimplificadoRequest();
				cadastro.setEmail(cEmail.getText());
				cadastro.setNomeFantasia(cNomeFantasia.getText());
				cadastro.setSobrenomeSocial(cSobrenomeSocial.getText());
				
				Response<CredenciamentoResponse> response= service.realizarPrimeiroAcesso(cCpfCnpj.getText().replaceAll("\\D", ""), cadastro);
				
				SSMensagem.informa(response.getStatus().getMessage());
				this.fechar();	
				FrmNovaSenha frm = Context.getBean(FrmNovaSenha.class);
				frm.exibir(response.getBody());
				
				
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			LOGGER.error("ERRO AO REQUISITAR API", ex);
			SSMensagem.erro("Erro ao tentar executar a operação");
		}
	}

	private void fechar() {
		this.dispose();
	}
}
