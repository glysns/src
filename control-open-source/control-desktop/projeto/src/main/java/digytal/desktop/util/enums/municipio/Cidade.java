package digytal.desktop.util.enums.municipio;

import java.io.Serializable;

public class Cidade implements Municipio {
	private Serializable codigo;
	private String nome;
	private String sigla;
	private String estadoNome;
	private String estadoSigla;
	private Integer uf;
	private Integer ibge;
	
	public Cidade(Serializable codigo, String nome, String sigla, String estadoNome, String estadoSigla, Integer uf,
			Integer ibge) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.sigla = sigla;
		this.estadoNome = estadoNome;
		this.estadoSigla = estadoSigla;
		this.uf = uf;
		this.ibge = ibge;
	}


	public Serializable getCodigo() {
		return codigo;
	}


	public String getNome() {
		return nome;
	}



	public String getSigla() {
		return sigla;
	}

	
	public String getEstadoNome() {
		return estadoNome;
	}



	public String getEstadoSigla() {
		return estadoSigla;
	}

	
	public Integer getUf() {
		return uf;
	}


	public Integer getIbge() {
		return ibge;
	}


}
