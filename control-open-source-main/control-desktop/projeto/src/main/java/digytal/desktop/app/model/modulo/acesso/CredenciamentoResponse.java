package digytal.desktop.app.model.modulo.acesso;

import lombok.Data;

@Data
public class CredenciamentoResponse {
    private Long expiracao;
    private Integer usuario;
    private String login;
    private String nome;
    private String token;
}
