package digytal.desktop.util.enums.municipio.ufs;

import digytal.desktop.util.enums.municipio.Municipio;

public enum DistritoFederal implements Municipio {
	BRASILIA (5300108,"BRASILIA"),
	;
	private Integer codigo;
	private String nome;
	private DistritoFederal(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public String getEstadoNome() {
		return "DISTRITO FEDERAL";
	}
	public String getEstadoSigla() {
		return "DF";
	}
	@Override
	public String getSigla() {
		return "NÃO INFORMADO";
	}
	public Integer getUf() {
		return 53;
	}
	@Override
	public Integer getIbge() {
		return Integer.valueOf(codigo);
	}

}
