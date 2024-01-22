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

import digytal.desktop.app.model.modulo.acesso.empresa.pagamento.FormaPagamentoCadastroResponse;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.financeiro.request.FormaParcelamentoRequest;
import digytal.desktop.app.utils.Calculos;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSCaixaCombinacao;
import digytal.desktop.components.desktop.ss.SSCampoDataHora;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.util.utils.business.BusinessException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamentoParcelado extends FrmPagamento {
	private SSCampoNumero cValorPago = new SSCampoNumero();
	private SSCaixaCombinacao cNumeroParcelas = new SSCaixaCombinacao();
	private SSCampoDataHora cDataVencimento = new SSCampoDataHora();

	private SSBotao bParcelamento = new SSBotao();
	private SSBotao bFechar = new SSBotao();

	private SSCampoNumero cValorOriginal = new SSCampoNumero();
	private SSCampoNumero cTaxa = new SSCampoNumero();

	public FrmPagamentoParcelado() {
		init();
	}

	public void init() {
		// setSize(400, 400);
		super.setTitulo("À Prazo");
		super.setDescricao("Pagamento parcelado");

		GridBagConstraints gbc_cValorOriginal = new GridBagConstraints();
		gbc_cValorOriginal.gridwidth = 2;
		gbc_cValorOriginal.weightx = 1.0;
		gbc_cValorOriginal.insets = new Insets(5, 5, 5, 0);
		gbc_cValorOriginal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorOriginal.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorOriginal.gridx = 0;
		gbc_cValorOriginal.gridy = 0;
		cValorOriginal.setComponenteFonte(new Font("Tahoma", Font.BOLD, 13));
		cValorOriginal.setComponenteNegrito(true);
		cValorOriginal.setComponenteCorFonte(Color.BLACK);
		getConteudo().add(cValorOriginal, gbc_cValorOriginal);

		GridBagConstraints gbc_cTaxa = new GridBagConstraints();
		gbc_cTaxa.weightx = 1.0;
		gbc_cTaxa.insets = new Insets(5, 5, 5, 5);
		gbc_cTaxa.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTaxa.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTaxa.gridx = 2;
		gbc_cTaxa.gridy = 0;
		cTaxa.setComponenteFonte(new Font("Tahoma", Font.BOLD, 13));
		getConteudo().add(cTaxa, gbc_cTaxa);

		GridBagConstraints gbc_cValorPago = new GridBagConstraints();
		gbc_cValorPago.weightx = 1.0;
		gbc_cValorPago.insets = new Insets(5, 5, 5, 0);
		gbc_cValorPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorPago.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorPago.gridx = 0;
		gbc_cValorPago.gridy = 1;
		cValorPago.getComponente().setFont(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setColunas(6);
		cValorPago.setComponenteFonte(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setComponenteNegrito(true);
		getConteudo().add(cValorPago, gbc_cValorPago);

		GridBagConstraints gbc_bBoleto = new GridBagConstraints();
		gbc_bBoleto.gridwidth = 3;
		gbc_bBoleto.weighty = 1.0;
		gbc_bBoleto.weightx = 1.0;
		gbc_bBoleto.anchor = GridBagConstraints.NORTHWEST;
		gbc_bBoleto.insets = new Insets(5, 5, 5, 5);
		gbc_bBoleto.fill = GridBagConstraints.HORIZONTAL;
		gbc_bBoleto.gridx = 0;
		gbc_bBoleto.gridy = 2;

		getConteudo().add(bParcelamento, gbc_bBoleto);

		GridBagConstraints gbc_bDataVencimento = new GridBagConstraints();
		gbc_bDataVencimento.insets = new Insets(5, 5, 5, 0);
		gbc_bDataVencimento.anchor = GridBagConstraints.NORTHWEST;
		gbc_bDataVencimento.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDataVencimento.gridx = 1;
		gbc_bDataVencimento.gridy = 1;
		cDataVencimento.setColunas(6);
		cDataVencimento.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		cDataVencimento.setRotulo("1° Parcela");
		getConteudo().add(cDataVencimento, gbc_bDataVencimento);

		GridBagConstraints gbc_bNrParcelas = new GridBagConstraints();
		gbc_bNrParcelas.insets = new Insets(5, 5, 5, 5);
		gbc_bNrParcelas.anchor = GridBagConstraints.NORTHWEST;
		gbc_bNrParcelas.fill = GridBagConstraints.HORIZONTAL;
		gbc_bNrParcelas.gridx = 2;
		gbc_bNrParcelas.gridy = 1;
		cNumeroParcelas.setComponenteFonte(new Font("Tahoma", Font.BOLD, 14));
		cNumeroParcelas.setRotulo("N° Parcelas");
		getConteudo().add(cNumeroParcelas, gbc_bNrParcelas);

		getRodape().add(bFechar);

		cValorPago.setRotulo("R$ Á Pagar");

		cValorPago.setFormato(Formato.MOEDA);
		cValorPago.setMascara(Formato.MOEDA);

		bParcelamento.setText("Pagamento Parcelado");
		bParcelamento.setIcone("date");
		bFechar.setText("Fechar");
		bFechar.setIcone("close");

		cTaxa.setRotulo("Tx%");
		cTaxa.setFormato(Formato.TAXA);
		cTaxa.setEditavel(false);
		cValorPago.setEditavel(false);
		cValorOriginal.setRotulo("R$ Original");
		cValorOriginal.setFormato(Formato.MOEDA);
		cValorOriginal.setMascara(Formato.MOEDA);
	}

	class ItemChangeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				calcular();
			}
		}
	}

	private void definirEventos() {
		bParcelamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});

		cNumeroParcelas.addItemListener(new ItemChangeListener());
		cValorOriginal.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

			}

			public void focusLost(FocusEvent e) {
				calcular();
			}
		});

		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});

	}

	public void iniciar(Double valorInformado) {
		definirEventos();
		try {
			cNumeroParcelas.setPrimeiroElementoVazio(false);
			cNumeroParcelas.setItens(contaService.consultarFormasPagamento(MeioPagamento.PRAZO), "descricao");
			this.valorInformado = valorInformado;
			cValorOriginal.setNumero(valorInformado);
			calcular();
			cDataVencimento.setData(LocalDate.now().plusMonths(1));
			cValorPago.requestFocus();
		} catch (BusinessException be) {
			SSMensagem.avisa(be.getMessage());
			// bBoleto.setEnabled(false);

		} catch (Exception be) {
			SSMensagem.erro(be.getMessage());
		}

	}

	private void calcular() {
		valorInformado = cValorOriginal.getDouble();
		calcularValorPagar(MeioPagamento.PRAZO, getNumeroParcela());
		cValorPago.setNumero(formaPagamento.getValorPago());
		cTaxa.setNumero(formaPagamento.getTaxaPagamento());
		adicionarParcelamento(cDataVencimento.getLocalDate(), getNumeroParcela());

		FormaParcelamentoRequest parc = formaPagamento.getParcelamento();
		Double valorParcela = Calculos.dividir(parc.getValorParcela(),
				Double.parseDouble(parc.getNumeroParcelas().toString()));
		bParcelamento.setText(String.format("Pagamento em %d x de %.2f", parc.getNumeroParcelas(), valorParcela));
		bParcelamento.setIcone("date");
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
				calcular();
				fechar(formaPagamento);
			}
		} catch (BusinessException be) {
			SSMensagem.avisa(be.getMessage());
		} catch (Exception be) {
			SSMensagem.erro(be.getMessage());
		}
	}

}
