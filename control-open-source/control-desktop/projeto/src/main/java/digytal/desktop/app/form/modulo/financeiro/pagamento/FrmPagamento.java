package digytal.desktop.app.form.modulo.financeiro.pagamento;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import digytal.desktop.app.model.modulo.acesso.empresa.pagamento.FormaPagamentoCadastroResponse;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.financeiro.request.FormaPagamentoRequest;
import digytal.desktop.app.model.modulo.financeiro.request.FormaParcelamentoRequest;
import digytal.desktop.app.service.modulo.acesso.conta.ContaService;
import digytal.desktop.app.utils.Calculos;
import digytal.desktop.components.desktop.Formulario;


public abstract class FrmPagamento extends Formulario {
	@Autowired
	protected ContaService contaService;
	protected Double valorInformado;
	protected FormaPagamentoRequest formaPagamento = new FormaPagamentoRequest();
	public FrmPagamento() {
	}
	
	public abstract void iniciar(Double valorInformado);
	
	protected FormaPagamentoRequest calcularValorPagar(MeioPagamento meioPagamento,Integer numeroParcelas) {
		FormaPagamentoCadastroResponse res = contaService.localizarFormaPagamento(meioPagamento,numeroParcelas);
		formaPagamento.setTaxaPagamento(res.getTaxa());
		formaPagamento.setValorOriginal(valorInformado);
		formaPagamento.setMeioPagamento(meioPagamento);
		formaPagamento.setValorPago(Calculos.somar(valorInformado, Calculos.calcularPorcentagem(valorInformado, res.getTaxa())));
		return formaPagamento;
	}
	protected void adicionarParcelamento(LocalDate dataPrimeiroVencimento, Integer numeroParcelas ) {
		FormaParcelamentoRequest parcelamento = new FormaParcelamentoRequest();
		parcelamento.setDataPrimeiroVencimento(dataPrimeiroVencimento);
		parcelamento.setNumeroParcelas(numeroParcelas);
		parcelamento.setValorParcela(formaPagamento.getValorPago());
		formaPagamento.setParcelamento(parcelamento);
	}
	/* 
	public static void main(String[] args) {
		Double valor = new BigDecimal("0.125").setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		System.out.println(valor);
		
		valor = new BigDecimal("0.155").setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		System.out.println(valor);
	}
	*/

}
