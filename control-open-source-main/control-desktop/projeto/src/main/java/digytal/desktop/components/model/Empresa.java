package digytal.desktop.components.model;

import lombok.Data;

@Data
public class Empresa {
	private Integer id;
	private String cpfCnpj;
	private String nomeFantasia;
	private String sobrenomeSocial;
	private Integer organizacao;
	private boolean padrao;
}
