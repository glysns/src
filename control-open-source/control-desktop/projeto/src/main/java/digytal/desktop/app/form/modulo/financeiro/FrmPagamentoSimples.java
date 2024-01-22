package digytal.desktop.app.form.modulo.financeiro;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.form.modulo.cadastro.cadastro.FrmLocalizaCadastro;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoResponse;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.cadastro.CadastroResponse;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.financeiro.request.FormaPagamentoRequest;
import digytal.desktop.app.model.modulo.financeiro.request.TransacaoRequest;
import digytal.desktop.app.service.modulo.acesso.AplicacaoService;
import digytal.desktop.app.service.modulo.financeiro.PagamentoService;
import digytal.desktop.app.utils.Cores;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.Formulario;
import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSCaixaCombinacao;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaEvento;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaListener;
import digytal.desktop.components.desktop.ss.util.SSFormatador;
import digytal.desktop.util.utils.business.BusinessException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamentoSimples extends Formulario {
	private SSCampoNumero cValor = new SSCampoNumero();
	private SSBotao bDinheiro = new SSBotao();
	private SSBotao bPix = new SSBotao();
	private SSBotao bDebito = new SSBotao();
	private SSBotao bCredito = new SSBotao();
	private SSBotao bBoleto = new SSBotao();
	private SSBotao bPrazo = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private JTextArea cDescricao = new JTextArea(3, 50);
	private GridBagConstraints gbc_1;
	private SSCaixaCombinacao cNatureza = new SSCaixaCombinacao();
	private SSCaixaCombinacao cCentroCusto = new SSCaixaCombinacao();
	private SSCampoTexto cCadastro = new SSCampoTexto();
	@Autowired
	private PagamentoService service;
	@Autowired
	private AplicacaoService aplicacaoService;
	private Integer cadastro=1;
	private AplicacaoTipo tipo;
	private JLabel bMais = new JLabel("Mais");
	public FrmPagamentoSimples() {
		JScrollPane scrollDescricao = new JScrollPane();
		cDescricao.setFont(new Font("Monospaced", Font.PLAIN, 12));
		cDescricao.setColumns(40);
		cDescricao.setLineWrap(true);
		scrollDescricao.setViewportView(cDescricao);
		scrollDescricao.setSize(250, 50);

		GridBagConstraints gbc_cValor = new GridBagConstraints();
		gbc_cValor.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValor.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValor.insets = new Insets(5, 5, 0, 5);
		gbc_cValor.gridx = 0;
		gbc_cValor.gridy = 0;
		getConteudo().add(cValor, gbc_cValor);

		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.weighty = 1.0;
		gbc_cNome.weightx = 1.0;
		gbc_cNome.fill = GridBagConstraints.BOTH;
		gbc_cNome.insets = new Insets(5, 5, 0, 5);
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 1;
		getConteudo().add(scrollDescricao, gbc_cNome);
		bMais.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		
		bMais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toogleCampos();
			}			
		});
		bMais.setForeground(Color.BLUE.darker());
		bMais.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_cMais = new GridBagConstraints();
		gbc_cMais.fill = GridBagConstraints.HORIZONTAL;
		gbc_cMais.anchor = GridBagConstraints.NORTHWEST;
		gbc_cMais.insets = new Insets(3, 7, 3, 0);
		gbc_cMais.gridx = 0;
		gbc_cMais.gridy = 2;
		getConteudo().add(bMais, gbc_cMais);
		
		
		GridBagConstraints gbc_cCC = new GridBagConstraints();
		gbc_cCC.anchor = GridBagConstraints.NORTHWEST;
		gbc_cCC.weighty = 1.0;
		gbc_cCC.weightx = 1.0;
		gbc_cCC.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCC.insets = new Insets(0, 7, 0, 5);
		gbc_cCC.gridx = 0;
		gbc_cCC.gridy = 3;
		cCentroCusto.setRotulo("Centro de Custo");
		getConteudo().add(cCentroCusto, gbc_cCC);
		
		GridBagConstraints gbc_cNatu = new GridBagConstraints();
		gbc_cNatu.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNatu.weightx = 1.0;
		gbc_cNatu.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNatu.insets = new Insets(0, 7, 3, 5);
		gbc_cNatu.gridx = 0;
		gbc_cNatu.gridy = 4;
		cNatureza.setRotulo("Natureza");
		getConteudo().add(cNatureza, gbc_cNatu);
		
		GridBagConstraints gbc_cCad = new GridBagConstraints();
		gbc_cCad.anchor = GridBagConstraints.NORTHWEST;
		gbc_cCad.weighty = 1.0;
		gbc_cCad.weightx = 1.0;
		gbc_cCad.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCad.insets = new Insets(0, 7, 0, 5);
		gbc_cCad.gridx = 0;
		gbc_cCad.gridy = 5;
		cCadastro.setComponenteCorFonte(new Color(0, 0, 255));
		cCadastro.setComponenteFonte(new Font("Tahoma", Font.BOLD, 11));
		cCadastro.setRotulo("Cadastro");
		cCadastro.setPesquisa(true);
		cCadastro.setEditavel(false, true);
		getConteudo().add(cCadastro, gbc_cCad);
		
		bDebito.setText("Débito");
		bDebito.setIcone("debitcard24");
		
		bBoleto.setText("Boleto");
		bBoleto.setIcone("barcode24");
		
		bPrazo.setText("À Prazo");
		bPrazo.setIcone("date-coin24");
		
		bCredito.setText("Crédito");
		bCredito.setIcone("creditcard24");
		
		bDinheiro.setText("Dinheiro");
		bDinheiro.setIcone("wallet24");
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		bFechar.setText("Fechar");
		bPix.setText("Via Pix");
		bPix.setIcone("pix24");
		setAlinhamentoBotoes(FlowLayout.CENTER);

		getRodape().setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(3, 3, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		getRodape().add(bDinheiro, gbc);
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(3, 3, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = 1;
		getRodape().add(bPix, gbc);

		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(3, 3, 0, 3);
		gbc.gridx = 0;
		gbc.gridy = 2;
		getRodape().add(bDebito, gbc);

		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(3, 3, 0, 3);
		gbc.gridx = 1;
		gbc.gridy = 0;
		getRodape().add(bCredito, gbc);
		
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(3, 3, 0, 3);
		gbc.gridx = 1;
		gbc.gridy = 1;
		getRodape().add(bBoleto, gbc);
		
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(3, 3, 0, 3);
		gbc.gridx = 1;
		gbc.gridy = 2;
		getRodape().add(bPrazo, gbc);

		gbc_1 = new GridBagConstraints();
		gbc_1.gridwidth = 2;
		gbc_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_1.weighty = 1.0;
		gbc_1.weightx = 1.0;
		gbc_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_1.insets = new Insets(3, 3, 3, 3);
		gbc_1.gridx = 0;
		gbc_1.gridy = 3;
		getRodape().add(bFechar, gbc_1);

		cValor.setFormato(Formato.MOEDA);
		cValor.setMascara(Formato.MOEDA);

		cValor.setRotulo("R$ VALOR");

		setSize(250, 436);
		
		bDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.DINHEIRO);
			}
		});
		
		bPix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.PIX);
			}
		});
		
		bDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.DEBITO);
			}
		});
		bCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.CREDITO);
			}
		});
		bBoleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.BOLETO);
			}
		});
		bPrazo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.PRAZO);
			}
		});
		
		cCadastro.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				localizarCadastro();
			}
		});

	}
	private void localizarCadastro() {
		cadastro=1;
		cCadastro.setRotulo("Cadastro");
		cCadastro.setText("**CLIENTE NAO INFORMADO**");
		
		FrmLocalizaCadastro frm = Context.getBean(FrmLocalizaCadastro.class);
		frm.inicializar(tipo==AplicacaoTipo.DESPESA);
		CadastroResponse cadastroResponse = (CadastroResponse) Formulario.dialogo(frm);

		if (cadastroResponse != null) {
			cCadastro.setRotulo("Cadastro: " + SSFormatador.formatarCpfCnpj(cadastroResponse.getCpfCnpj()));
			cCadastro.setText(cadastroResponse.getNomeFantasia());
			cadastro = cadastroResponse.getId();
		}
	}
	private void toogleCampos() {
		boolean mais = bMais.getText().equalsIgnoreCase("mais");
		cNatureza.setVisible(mais);
		cCadastro.setVisible(mais);
		if(AplicacaoTipo.DESPESA == tipo)
			cCentroCusto.setVisible(mais);
		bMais.setText(mais?"Menos":"Mais");
	}
	public void inicializar(AplicacaoTipo tipo) {
		this.tipo = tipo;
		cValor.setComponenteNegrito(true);
		cValor.setNumero(0.0);
		cNatureza.setVisible(false);
		cCentroCusto.setVisible(false);
		cCadastro.setVisible(false);
		if (AplicacaoTipo.DESPESA == tipo) {
			cDescricao.setText("Despesas Diversas");
			cValor.setComponenteCorFonte(Cores.VERMELHO);
			setTitulo("Pagamento");
			setDescricao("Lancamento de despesas");
			cCentroCusto.setItens(aplicacaoService.listarAreas(""), "nome");
			cNatureza.setItens(aplicacaoService.listarDespesas(""), "nome");
			
		} else {
			cDescricao.setText("Receitas Diversas");
			cValor.setComponenteCorFonte(Cores.AZUL);
			setTitulo("Recebimento");
			setDescricao("Lancamento de receitas");
			cNatureza.setItens(aplicacaoService.listarReceitas(""), "nome");
		}
	}

	private void confirmar(MeioPagamento meioPagamento) {
		try {
			if (SSMensagem.pergunta("Confirma lançar esta " + tipo.getDescricao())) {
				TransacaoRequest request = new TransacaoRequest();
				request.setCadastro(cadastro);
				request.setDescricao(cDescricao.getText());
				request.setValor(cValor.getDouble());
				FormaPagamentoRequest rateio = new FormaPagamentoRequest();
				rateio.setValorOriginal(cValor.getDouble());
				rateio.setValorPago(cValor.getDouble());
				rateio.setTaxaPagamento(0.0);
				rateio.setMeioPagamento(meioPagamento);
				request.getFormasPagamento().add(rateio);
				
				AplicacaoResponse cc = (AplicacaoResponse) cCentroCusto.getValue();
				AplicacaoResponse nat = (AplicacaoResponse) cNatureza.getValue();
				
				if(cc!=null)
					request.setArea(cc.getId());
				
				if(nat!=null)
					request.setNatureza(nat.getId());
				
				service.inserir(tipo, request);
			}
			SSMensagem.informa("Lançamento realizado com sucesso");
			fechar();

		} catch (BusinessException e) {
			SSMensagem.avisa(e.getMessage());
		}

	}
}
