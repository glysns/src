package digytal.desktop.app.model.modulo.financeiro.request;

import java.util.Objects;

import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import lombok.Data;

@Data
public class FormaPagamentoRequest {
	private MeioPagamento meioPagamento;
	private Double valorOriginal;
	private Double taxaPagamento;
	private Double valorPago;
	private FormaParcelamentoRequest parcelamento = new FormaParcelamentoRequest();
	public Double getValorAcrescimo() {
		return valorPago - valorOriginal;
	}
	private Long hash;
    public FormaPagamentoRequest() {
    	this.hash = System.currentTimeMillis();
    }
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormaPagamentoRequest other = (FormaPagamentoRequest) obj;
		return Objects.equals(hash, other.hash);
	}
	@Override
	public int hashCode() {
		return Objects.hash(hash);
	}
}
