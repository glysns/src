package digytal.desktop.util.enums.municipio;

import digytal.desktop.util.enums.Enumeracao;

public interface Municipio extends Enumeracao {
	String getSigla();
	String getEstadoNome();
	String getEstadoSigla();
	Integer getUf();
	Integer getIbge();
}
