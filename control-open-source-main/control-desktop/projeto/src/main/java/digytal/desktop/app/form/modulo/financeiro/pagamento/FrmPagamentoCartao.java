package digytal.desktop.app.form.modulo.financeiro.pagamento;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.form.modulo.financeiro.pagamento.FrmPagamentoParcelado.ItemChangeListener;
import digytal.desktop.app.model.comum.TipoCartao;
import digytal.desktop.app.model.modulo.acesso.empresa.pagamento.FormaPagamentoCadastroResponse;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.financeiro.request.FormaParcelamentoRequest;
import digytal.desktop.app.utils.Calculos;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSCaixaCombinacao;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.util.utils.business.BusinessException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamentoCartao extends FrmPagamento {
	private SSCampoNumero cValorPago = new SSCampoNumero();
	private SSCaixaCombinacao cNumeroParcelas = new SSCaixaCombinacao();
	private SSCaixaCombinacao cTipoCartao = new SSCaixaCombinacao();
	private SSBotao bPagamento = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private SSCampoNumero cValorOriginal = new SSCampoNumero();
	private SSCampoNumero cTaxa = new SSCampoNumero();
	private MeioPagamento meioPagamento = MeioPagamento.DEBITO;
	public FrmPagamentoCartao() {
		init();
	}

	public void init() {
		// setSize(400, 400);
		super.setTitulo("Cartão");
		super.setDescricao("Pagamento via Débito ou Crédito");

		GridBagConstraints gbc_cValorOriginal = new GridBagConstraints();
		gbc_cValorOriginal.weightx = 1.0;
		gbc_cValorOriginal.insets = new Insets(5, 5, 5, 0);
		gbc_cValorOriginal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorOriginal.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorOriginal.gridx = 0;
		gbc_cValorOriginal.gridy = 1;
		cTipoCartao.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		cValorOriginal.setComponenteFonte(new Font("Tahoma", Font.BOLD, 13));
		cValorOriginal.setComponenteNegrito(true);
		cValorOriginal.setComponenteCorFonte(Color.BLUE);
		getConteudo().add(cValorOriginal, gbc_cValorOriginal);

		GridBagConstraints gbc_cTipoCartao = new GridBagConstraints();
		gbc_cTipoCartao.gridwidth = 2;
		gbc_cTipoCartao.weightx = 1.0;
		gbc_cTipoCartao.insets = new Insets(5, 5, 5, 5);
		gbc_cTipoCartao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTipoCartao.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTipoCartao.gridx = 0;
		gbc_cTipoCartao.gridy = 0;
		cTipoCartao.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		getConteudo().add(cTipoCartao, gbc_cTipoCartao);

		GridBagConstraints gbc_cTaxa = new GridBagConstraints();
		gbc_cTaxa.gridwidth = 1;
		gbc_cTaxa.weightx = 1.0;
		gbc_cTaxa.insets = new Insets(5, 5, 5, 5);
		gbc_cTaxa.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTaxa.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTaxa.gridx = 1;
		gbc_cTaxa.gridy = 1;
		cTaxa.setComponenteFonte(new Font("Tahoma", Font.BOLD, 13));
		getConteudo().add(cTaxa, gbc_cTaxa);

		GridBagConstraints gbc_cValorPago = new GridBagConstraints();
		gbc_cValorPago.weightx = 1.0;
		gbc_cValorPago.insets = new Insets(5, 5, 5, 0);
		gbc_cValorPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorPago.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorPago.gridx = 0;
		gbc_cValorPago.gridy = 2;
		cValorPago.getComponente().setFont(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setColunas(6);
		cValorPago.setComponenteFonte(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setComponenteNegrito(true);
		getConteudo().add(cValorPago, gbc_cValorPago);

		GridBagConstraints gbc_bPagamento = new GridBagConstraints();
		gbc_bPagamento.gridwidth = 2;
		gbc_bPagamento.weighty = 1.0;
		gbc_bPagamento.weightx = 1.0;
		gbc_bPagamento.anchor = GridBagConstraints.NORTHWEST;
		gbc_bPagamento.insets = new Insets(5, 5, 5, 5);
		gbc_bPagamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPagamento.gridx = 0;
		gbc_bPagamento.gridy = 3;

		getConteudo().add(bPagamento, gbc_bPagamento);

		GridBagConstraints gbc_bNrParcelas = new GridBagConstraints();
		gbc_bNrParcelas.insets = new Insets(5, 5, 5, 5);
		gbc_bNrParcelas.anchor = GridBagConstraints.NORTHWEST;
		gbc_bNrParcelas.fill = GridBagConstraints.HORIZONTAL;
		gbc_bNrParcelas.gridx = 1;
		gbc_bNrParcelas.gridy = 2;
		//cNumeroParcelas.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		cNumeroParcelas.setRotulo("N° Parcelas");
		getConteudo().add(cNumeroParcelas, gbc_bNrParcelas);

		getRodape().add(bFechar);

		cValorPago.setRotulo("R$ Á Pagar");
		cValorPago.setFormato(Formato.MOEDA);
		cValorPago.setMascara(Formato.MOEDA);
		cValorPago.setEditavel(false);
		;

		cValorOriginal.setRotulo("R$ Original");
		cValorOriginal.setFormato(Formato.MOEDA);
		cValorOriginal.setMascara(Formato.MOEDA);

		cTaxa.setRotulo("Tx%");
		cTaxa.setFormato(Formato.TAXA);
		cTaxa.setEditavel(false);

		bPagamento.setText("Pagamento");
		bPagamento.setIcone("date");
		bFechar.setText("Fechar");
		bFechar.setIcone("close");
		cTipoCartao.setRotulo("Cartão");
		cTipoCartao.setPrimeiroElementoVazio(false);

	}
	private void definirEventos() {
		bPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});

		cTipoCartao.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					atualizarParcelas();
				}
			}
		});

		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		cNumeroParcelas.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					calcular();
				}
			}
		});
		cValorOriginal.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

			}

			public void focusLost(FocusEvent e) {
				calcular();
			}
		});
	}
	public void iniciar(Double valorInformado) {
		try {
			MeioPagamento[] cartoes = {MeioPagamento.DEBITO, MeioPagamento.CREDITO};
			cTipoCartao.setPrimeiroElementoVazio(false);
			cTipoCartao.setItens(cartoes);
			cNumeroParcelas.setPrimeiroElementoVazio(false);
			cNumeroParcelas.setItens(contaService.consultarFormasPagamento(meioPagamento), "descricao");
			this.valorInformado = valorInformado;
			cValorOriginal.setNumero(valorInformado);
			cValorPago.requestFocus();
			calcular();
			definirEventos();
		} catch (BusinessException be) {
			SSMensagem.avisa(be.getMessage());
		} catch (Exception be) {
			SSMensagem.erro(be.getMessage());
		}
	}
	private void atualizarParcelas() {
		meioPagamento = (MeioPagamento) cTipoCartao.getValue();
		cNumeroParcelas.setItens(contaService.consultarFormasPagamento(meioPagamento), "descricao");
		calcular();
	}

	private void calcular() {
		valorInformado = cValorOriginal.getDouble();
		calcularValorPagar(meioPagamento, getNumeroParcela());
		cValorPago.setNumero(formaPagamento.getValorPago());
		cTaxa.setNumero(formaPagamento.getTaxaPagamento());
		adicionarParcelamento(LocalDate.now(), getNumeroParcela());

		FormaParcelamentoRequest parc = formaPagamento.getParcelamento();
		Double valorParcela = Calculos.dividir(parc.getValorParcela(),Double.parseDouble(parc.getNumeroParcelas().toString()));
		bPagamento.setText(String.format("Pagamento em %d x de %.2f", parc.getNumeroParcelas(), valorParcela));
		bPagamento.setIcone(meioPagamento ==MeioPagamento.CREDITO ? "date" : "today");
		
	}
	private Integer getNumeroParcela() {
		FormaPagamentoCadastroResponse item = (FormaPagamentoCadastroResponse) cNumeroParcelas.getValue();
		if (item != null)
			return item.getNumeroParcelas();
		else
			return 1;
	}
	private void confirmar() {
		try {
			if (SSMensagem.confirma("Confirma adicionar este pagamento")) {
				if (valorInformado.compareTo(cValorOriginal.getDouble()) < 0) {
					SSMensagem.avisa("Valor informado é superior ao valor restante");
					return;
				}
				fechar(formaPagamento);
			}
		} catch (BusinessException be) {
			SSMensagem.avisa(be.getMessage());
		} catch (Exception be) {
			SSMensagem.erro(be.getMessage());
		}
	}

}
