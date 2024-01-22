package digytal.desktop.app.model.modulo.financeiro.request;

import java.util.Objects;

import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import lombok.Data;

@Data
public class ParcelaPagamentoRequest {
	private Double valor;
	private MeioPagamento meioPagamento;
	private Long hash;
    public ParcelaPagamentoRequest() {
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
		ParcelaPagamentoRequest other = (ParcelaPagamentoRequest) obj;
		return Objects.equals(hash, other.hash);
	}
	@Override
	public int hashCode() {
		return Objects.hash(hash);
	}
}
