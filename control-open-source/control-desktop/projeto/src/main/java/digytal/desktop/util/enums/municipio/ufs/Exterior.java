package digytal.desktop.util.enums.municipio.ufs;

import digytal.desktop.util.enums.municipio.Municipio;

public enum Exterior implements Municipio {
	EXTERIOR (9999999,"EXTERIOR"),
	;
	private Integer codigo;
	private String nome;
	private Exterior(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public Integer getUf() {
		return 0;
	}
	public String getEstadoNome() {
		return "EXTERIOR";
	}
	public String getEstadoSigla() {
		return "EX";
	}
	@Override
	public String getSigla() {
		return "NÃO INFORMADO";
	}
	@Override
	public Integer getIbge() {
		return Integer.valueOf(codigo);
	}
}
