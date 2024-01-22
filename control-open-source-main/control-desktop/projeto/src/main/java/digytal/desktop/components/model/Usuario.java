package digytal.desktop.components.model;

import java.util.List;

import lombok.Data;

@Data
public class Usuario {
	private String login;
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private boolean bloqueado;
    private boolean expirado;
    private Integer perfil;
    private boolean consultor;
    private List<Empresa> empresas;
}
