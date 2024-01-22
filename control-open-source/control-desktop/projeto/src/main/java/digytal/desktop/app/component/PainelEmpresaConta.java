package digytal.desktop.app.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.springframework.beans.BeanUtils;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.form.modulo.param.FrmLocalizaBanco;
import digytal.desktop.app.model.modulo.acesso.empresa.conta.ContaFatura;
import digytal.desktop.app.model.modulo.acesso.empresa.conta.ContaRequest;
import digytal.desktop.app.model.modulo.acesso.empresa.conta.ContaResponse;
import digytal.desktop.app.model.modulo.acesso.empresa.pagamento.FormaPagamentoCadastroRequest;
import digytal.desktop.app.model.modulo.acesso.empresa.pagamento.FormaPagamentoCadastroResponse;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.param.BancoResponse;
import digytal.desktop.app.service.modulo.acesso.conta.ContaService;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.Formulario;
import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSCaixaCombinacao;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSCampoSelecao;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSGrade;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaEvento;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaListener;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

public class PainelEmpresaConta extends JPanel {
	private SSGrade tContas = new SSGrade();
	private SSCampoTexto cAgencia = new SSCampoTexto();
	private SSCampoTexto cNumero = new SSCampoTexto();
	private SSCampoTexto cLegenda = new SSCampoTexto();
	private SSCampoNumero cSaldo = new SSCampoNumero();
	private SSCampoSelecao cPadrao = new SSCampoSelecao();
	private SSCampoSelecao cCredito = new SSCampoSelecao();
	private SSBotao bConfirma = new SSBotao();
	private SSBotao bNovo = new SSBotao();
	private SSCampoNumero cDiaIntervalo = new SSCampoNumero();
	private SSCampoNumero cDiaVencimento = new SSCampoNumero();
	private SSCampoTexto cBanco = new SSCampoTexto();
	private SSCampoTexto cChavePix = new SSCampoTexto();
	private SSCaixaCombinacao cFormaPagamento = new SSCaixaCombinacao();
	private SSCampoNumero cFormaPagamentoTaxa = new SSCampoNumero();
	private SSCampoNumero cFormaPagamentoNrParcelas = new SSCampoNumero();
	private SSGrade tFormasPagamento = new SSGrade();
	private JTextArea cDescricao = new JTextArea(3,50);
	private ContaResponse registro;
	private List<FormaPagamentoCadastroResponse> formasPagamento = new ArrayList();
	private ContaService service = Context.getBean(ContaService.class);
	public PainelEmpresaConta() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scroll = new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(100, 0));
		scroll.setViewportView(tContas);
		
		tContas.adicionarColuna(0, "Agência", "agencia");
		tContas.adicionarColuna(1, "Número", "numero");
		tContas.adicionarColuna(2, "Legenda", "legenda");
		tContas.adicionarColuna(3, "Saldo", "saldo");
		tContas.definirLarguraColunas(55, 65,220, 80);
		tContas.getModeloColuna().setFormato(3, Formato.MOEDA);
		cSaldo.setFormato(Formato.MOEDA);
		cSaldo.setMascara(Formato.MOEDA);
		
		tFormasPagamento.adicionarColuna(0, "Forma", "meioPagamento");
		tFormasPagamento.adicionarColuna(1, "N°Pc", "numeroParcelas");
		tFormasPagamento.adicionarColuna(2, "% Tx", "taxa");
		tFormasPagamento.getModeloColuna().setFormato(2, Formato.MOEDA);
		tFormasPagamento.definirLarguraColunas(80,40,40);
		
		JPanel pnlCampos = new JPanel(new GridBagLayout());
		add(scroll, BorderLayout.CENTER);
		add(pnlCampos, BorderLayout.SOUTH);
		
		GridBagConstraints gbc_tAgencia = new GridBagConstraints();
		gbc_tAgencia.weightx = 0.1;
		gbc_tAgencia.insets = new Insets(5, 5, 0, 0);
		gbc_tAgencia.fill = GridBagConstraints.HORIZONTAL;
		gbc_tAgencia.anchor = GridBagConstraints.NORTHWEST;
		gbc_tAgencia.gridx = 0;
		gbc_tAgencia.gridy = 0;
		pnlCampos.add(cAgencia, gbc_tAgencia);
		
		GridBagConstraints gbc_tNumero = new GridBagConstraints();
		gbc_tNumero.weightx = 0.1;
		gbc_tNumero.insets = new Insets(5, 5, 0, 0);
		gbc_tNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_tNumero.anchor = GridBagConstraints.NORTHWEST;
		gbc_tNumero.gridx = 1;
		gbc_tNumero.gridy = 0;
		pnlCampos.add(cNumero, gbc_tNumero);
		
		GridBagConstraints gbc_tSaldo = new GridBagConstraints();
		gbc_tSaldo.gridwidth = 2;
		gbc_tSaldo.insets = new Insets(5, 5, 0, 0);
		gbc_tSaldo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tSaldo.anchor = GridBagConstraints.NORTHWEST;
		gbc_tSaldo.gridx = 0;
		gbc_tSaldo.gridy = 2;
		pnlCampos.add(cSaldo, gbc_tSaldo);
		
		GridBagConstraints gbc_tBanco = new GridBagConstraints();
		gbc_tBanco.gridwidth = 3;
		gbc_tBanco.insets = new Insets(5, 5, 0, 5);
		gbc_tBanco.fill = GridBagConstraints.HORIZONTAL;
		gbc_tBanco.anchor = GridBagConstraints.NORTHWEST;
		gbc_tBanco.gridx = 2;
		gbc_tBanco.gridy = 1;
		pnlCampos.add(cBanco, gbc_tBanco);
		
		GridBagConstraints gbc_tLegenda = new GridBagConstraints();
		gbc_tLegenda.weightx = 0.7;
		gbc_tLegenda.gridwidth = 3;
		gbc_tLegenda.insets = new Insets(5, 5, 0, 5);
		gbc_tLegenda.fill = GridBagConstraints.HORIZONTAL;
		gbc_tLegenda.anchor = GridBagConstraints.NORTHWEST;
		gbc_tLegenda.gridx = 2;
		gbc_tLegenda.gridy = 0;
		pnlCampos.add(cLegenda, gbc_tLegenda);
		
		GridBagConstraints gbc_cChave = new GridBagConstraints();
		gbc_cChave.gridwidth = 2;
		gbc_cChave.insets = new Insets(5, 5, 0, 0);
		gbc_cChave.fill = GridBagConstraints.HORIZONTAL;
		gbc_cChave.anchor = GridBagConstraints.NORTHWEST;
		gbc_cChave.gridx = 0;
		gbc_cChave.gridy = 1;
		pnlCampos.add(cChavePix, gbc_cChave);
		
		GridBagConstraints gbc_cForma = new GridBagConstraints();
		gbc_cForma.gridwidth = 1;
		gbc_cForma.insets = new Insets(5, 5, 0, 0);
		gbc_cForma.fill = GridBagConstraints.HORIZONTAL;
		gbc_cForma.anchor = GridBagConstraints.NORTHWEST;
		gbc_cForma.gridx = 2;
		gbc_cForma.gridy = 2;
		pnlCampos.add(cFormaPagamento, gbc_cForma);
		
		GridBagConstraints gbc_cNrParcela = new GridBagConstraints();
		gbc_cNrParcela.gridwidth = 1;
		gbc_cNrParcela.insets = new Insets(5, 5, 0, 0);
		gbc_cNrParcela.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNrParcela.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNrParcela.gridx = 3;
		gbc_cNrParcela.gridy = 2;
		cFormaPagamentoNrParcelas.setRotulo("N° Parc.");
		pnlCampos.add(cFormaPagamentoNrParcelas, gbc_cNrParcela);
		
		GridBagConstraints gbc_cTaxa = new GridBagConstraints();
		gbc_cTaxa.gridwidth = 1;
		gbc_cTaxa.insets = new Insets(5, 5, 0, 5);
		gbc_cTaxa.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTaxa.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTaxa.gridx = 4;
		gbc_cTaxa.gridy = 2;
		pnlCampos.add(cFormaPagamentoTaxa, gbc_cTaxa);
		
		GridBagConstraints gbc_cDescricao = new GridBagConstraints();
		gbc_cDescricao.weighty = 1.0;
		gbc_cDescricao.weightx = 1.0;
		gbc_cDescricao.gridwidth = 6;
		gbc_cDescricao.insets = new Insets(5, 5, 0, 5);
		gbc_cDescricao.fill = GridBagConstraints.BOTH;
		gbc_cDescricao.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDescricao.gridx = 0;
		gbc_cDescricao.gridy = 3;
		JScrollPane scrollDescricao = new JScrollPane();
		cDescricao.setFont(new Font("Monospaced", Font.PLAIN, 12));
		cDescricao.setLineWrap(true);
		scrollDescricao.setViewportView(cDescricao);
		
		pnlCampos.add(scrollDescricao, gbc_cDescricao);
		
		JScrollPane scrollFormaPagamento = new JScrollPane(tFormasPagamento);
		scrollFormaPagamento.setPreferredSize(new Dimension(180,0));
		scrollFormaPagamento.setViewportView(tFormasPagamento);
		
		GridBagConstraints gbc_scrollFormaPagamento = new GridBagConstraints();
		gbc_scrollFormaPagamento.weighty = 1.0;
		gbc_scrollFormaPagamento.gridheight = 3;
		gbc_scrollFormaPagamento.gridwidth = 1;
		gbc_scrollFormaPagamento.insets = new Insets(15, 0, 10, 5);
		gbc_scrollFormaPagamento.weightx = 1.5;
		gbc_scrollFormaPagamento.fill = GridBagConstraints.BOTH;
		gbc_scrollFormaPagamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollFormaPagamento.gridx = 5;
		gbc_scrollFormaPagamento.gridy = 0;
		pnlCampos.add(scrollFormaPagamento, gbc_scrollFormaPagamento);
		
		FlowLayout fl_pAvancado = new FlowLayout();
		fl_pAvancado.setVgap(1);
		fl_pAvancado.setAlignment(FlowLayout.LEFT);
		JPanel pAvancado = new JPanel(fl_pAvancado);
		pAvancado.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Avan\u00E7ado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		
		GridBagConstraints gbc_pAvancado = new GridBagConstraints();
		gbc_pAvancado.gridwidth = 3;
		gbc_pAvancado.insets = new Insets(5, 5, 5, 5);
		gbc_pAvancado.weighty = 1.0;
		gbc_pAvancado.fill = GridBagConstraints.HORIZONTAL;
		gbc_pAvancado.anchor = GridBagConstraints.SOUTHWEST;
		gbc_pAvancado.gridx = 0;
		gbc_pAvancado.gridy = 4;
		pnlCampos.add(pAvancado, gbc_pAvancado);
		
		FlowLayout fl_pBotoes = new FlowLayout();
		fl_pBotoes.setVgap(1);
		fl_pBotoes.setAlignment(FlowLayout.LEFT);
		JPanel pBotoes = new JPanel(fl_pBotoes);
		
		
		pAvancado.add(cPadrao);
		pAvancado.add(cCredito);
		pAvancado.add(cDiaIntervalo);
		pAvancado.add(cDiaVencimento);
		pBotoes.add(bConfirma);
		pBotoes.add(bNovo);
		
		GridBagConstraints gbc_pBotoes = new GridBagConstraints();
		gbc_pBotoes.gridwidth = 3;
		gbc_pBotoes.insets = new Insets(5, 5, 5, 5);
		gbc_pBotoes.weighty = 1.0;
		gbc_pBotoes.fill = GridBagConstraints.HORIZONTAL;
		gbc_pBotoes.anchor = GridBagConstraints.SOUTHWEST;
		gbc_pBotoes.gridx = 3;
		gbc_pBotoes.gridy = 4;
		pnlCampos.add(pBotoes, gbc_pBotoes);
		
		bConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
			
		});
		
		bNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preparar();
			}
		});
		cDescricao.setLineWrap(true);
		cDescricao.setWrapStyleWord(true);
		
		definirPropriedades();
		definirEventos();
	}
	private void definirPropriedades() {
		cChavePix.setRotulo("Chave PIX");
		cFormaPagamento.setRotulo("Forma Pagto");
		
		cAgencia.setRotulo("Agência");
		cNumero.setRotulo("Número");
		cLegenda.setRotulo("Legenda");
		cSaldo.setRotulo("Saldo");
		cPadrao.setRotulo("Padrão ?");
		cPadrao.setVisible(false);
		cCredito.setRotulo("Fatura ?");
		cDiaIntervalo.setRotulo("Intervalo");
		cDiaVencimento.setRotulo("Vencimento");
		cFormaPagamentoTaxa.setRotulo("Taxa %");
		cBanco.setRotulo("Banco");
		
		bConfirma.setText("Confirmar");
		bConfirma.setIcone("ok");
		bNovo.setText("Novo");
		bNovo.setIcone("new");
		cBanco.setEditavel(false,true);
		cSaldo.setEditavel(false);
		cBanco.setPesquisa(true);
		cChavePix.setTudoMinusculo(true);
		cFormaPagamentoTaxa.setFormato(Formato.MOEDA);
		cFormaPagamentoTaxa.setMascara(Formato.MOEDA);
		cSaldo.setFormato(Formato.MOEDA);
		cSaldo.setMascara(Formato.MOEDA);
		cFormaPagamento.setItens(MeioPagamento.values(),"descricao");
		cFormaPagamentoTaxa.setPesquisa(true);
		cFormaPagamentoTaxa.setPesquisaIcone("add");
		
	}
	private void definirEventos() {
		tContas.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        if (mouseEvent.getClickCount() == 2) {
		            exibirConta();
		        }
		    }
		});
		tFormasPagamento.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        if (mouseEvent.getClickCount() == 2) {
		            excluirFormaPagamento();
		        }
		    }
		});
		ActionListener cCreditoActionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        checarContaCredito(selected);
	      }
	    };
	    cCredito.addActionListener(cCreditoActionListener);
	    
	    cBanco.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				localizarBanco();
			}
		});
	    cFormaPagamentoTaxa.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				adicionarContaFormaPagamento();
			}
		});
	}
	private void adicionarContaFormaPagamento() {
		try {
			if (SSMensagem.pergunta("Confirma adicionar esta forma de pagamento?")) {
				FormaPagamentoCadastroRequest formaPagamento = new FormaPagamentoCadastroRequest();
				formaPagamento.setMeioPagamento((MeioPagamento) cFormaPagamento.getValue());
				formaPagamento.setTaxa(cFormaPagamentoTaxa.getDouble());
				formaPagamento.setNumeroParcelas(cFormaPagamentoNrParcelas.getInteger());
				Response<Integer> response= service.incluirFormaPagamento(registro.getId(), formaPagamento);
				SSMensagem.informa("Forma da pagamento adicionada a conta selecionada");
				FormaPagamentoCadastroResponse forma = new FormaPagamentoCadastroResponse();
				forma.setId(response.getBody());
				forma.setMeioPagamento(formaPagamento.getMeioPagamento());
				forma.setTaxa(formaPagamento.getTaxa());
				forma.setNumeroParcelas(formaPagamento.getNumeroParcelas());
				formasPagamento.add(forma);
				tFormasPagamento.setValue(formasPagamento);
			
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			//LOGGER.error("ERRO AO REQUISITAR API", ex);
			SSMensagem.erro("Erro ao tentar executar a operação");
		}
	}
	private void confirmar() {
		try {
			if (SSMensagem.pergunta("Confirma concluir o cadastro da conta?")) {
				ContaRequest conta = criarRequest();
				if(registro!=null) {
					service.alterar(registro.getId(), conta);
				}else {
					ContaRequest novaConta = new ContaRequest();
					BeanUtils.copyProperties(conta, novaConta);
					service.incluir(novaConta);
				}
				exibirContas();
				SSMensagem.informa("Operação realizada com sucesso");
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			//LOGGER.error("ERRO AO REQUISITAR API", ex);
			SSMensagem.erro("Erro ao tentar executar a operação");
		}
	}
	private ContaRequest criarRequest() {
		ContaRequest request = new ContaRequest();
		request.setAgencia(cAgencia.getText());
		request.setNumero(cNumero.getText());
		
		request.setBanco(null);
		if(cBanco.getText()!=null && cBanco.getText().length() >0)
			request.setBanco(Integer.valueOf(cBanco.getText().split("\\-")[0].trim()));
		request.setLegenda(cLegenda.getText());
		//request.setContaPadrao(cPadrao.isSelected());
		request.setContaCredito(cCredito.isSelected());
		request.setChavePix(cChavePix.getText());
		request.setDescricao(cDescricao.getText());
		if(request.isContaCredito()) {
			ContaFatura fatura = new ContaFatura();
			fatura.setDiasIntervalo(cDiaIntervalo.getInteger());
			fatura.setDiaVencimento(cDiaVencimento.getInteger());
			request.setFatura(fatura);
		}
		return request;
	}
	private void preparar() {
		registro=null;
		cDescricao.setText("");
		cCredito.setEditavel(true);
		cAgencia.setText("");
		cNumero.setText("");
		cLegenda.setText("");
		cChavePix.setText("");
		cFormaPagamento.setValue(null);
		cSaldo.setValue(0.0);
		cFormaPagamentoTaxa.setValue(0.0);
		cCredito.setSelected(false);
		cPadrao.setSelected(false);
		cFormaPagamento.setEditavel(false);
		cFormaPagamentoTaxa.setEditavel(false);
		cFormaPagamentoNrParcelas.setEditavel(false);
		cFormaPagamentoNrParcelas.setNumero(1);
		
		checarContaCredito(false);
	}
	private void excluirFormaPagamento() {
		try {
			if (SSMensagem.pergunta("Confirma excluir esta forma de pagamento?")) {
				FormaPagamentoCadastroResponse res = (FormaPagamentoCadastroResponse) tFormasPagamento.getLinhaSelecionada();
				Response<Boolean> response= service.excluirFormaPagamento(res.getId());
				exibirFormasPagamento();
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			//LOGGER.error("ERRO AO REQUISITAR API", ex);
			SSMensagem.erro("Erro ao tentar executar a operação");
		}
	}
	private void exibirConta() {
		registro = (ContaResponse) tContas.getLinhaSelecionada();
		if(registro!=null) {
			cCredito.setEditavel(false);
			cFormaPagamento.setEditavel(true);
			cFormaPagamentoTaxa.setEditavel(true);
			cFormaPagamentoNrParcelas.setEditavel(true);
			
			cAgencia.setText(registro.getAgencia());
			cNumero.setText(registro.getNumero());
			cLegenda.setText(registro.getLegenda());
			cSaldo.setValue(registro.getSaldo());
			cCredito.setSelected(registro.isContaCredito());
			cBanco.setText(registro.getBanco().toString());
			cChavePix.setText(registro.getChavePix());
			cDescricao.setText(registro.getDescricao());
			checarContaCredito(registro.isContaCredito());
			exibirFormasPagamento();
		}else
			preparar();
	}
	private void exibirFormasPagamento() {
		formasPagamento = service.listarFormasPagamento(registro.getId());
		tFormasPagamento.setValue(formasPagamento);
	}
	private void checarContaCredito(boolean selected) {
		cDiaIntervalo.setNumero(null);
		cDiaVencimento.setNumero(null);
		cDiaIntervalo.setEditavel(selected);
		cDiaVencimento.setEditavel(selected);
		if(registro!=null && registro.getFatura()!=null) {
			cDiaIntervalo.setNumero(registro.getFatura().getDiasIntervalo());
			cDiaVencimento.setNumero(registro.getFatura().getDiaVencimento());
		}
	}
	public void exibirContas() {
		List<ContaResponse> registros = service.listar();
		tContas.setValue(registros);
		preparar();
	}
	private void localizarBanco() {
		cBanco.setText("");
		FrmLocalizaBanco frm = Context.getBean(FrmLocalizaBanco.class);
		BancoResponse banco = (BancoResponse) Formulario.dialogo(frm);	
		if(banco!=null)
			cBanco.setText(banco.getId() + " - " + banco.getLegenda() + " (compe: " + banco.getCompe() + ")");
	}
	
}
