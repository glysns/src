package digytal.desktop.app.model.modulo.contrato;

import java.util.Objects;

import lombok.Data;

@Data
public class ContratoItemRequest {
	private Integer produto;
	private Double preco;
	private String descricao;
	private String und;
	private Double quantidade;
	private Double valorUnitario;
	private Double valorAplicado;
	private Double valorVariacao;
	private Long hash;
    public ContratoItemRequest() {
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
		ContratoItemRequest other = (ContratoItemRequest) obj;
		return Objects.equals(hash, other.hash);
	}
	@Override
	public int hashCode() {
		return Objects.hash(hash);
	}
}
