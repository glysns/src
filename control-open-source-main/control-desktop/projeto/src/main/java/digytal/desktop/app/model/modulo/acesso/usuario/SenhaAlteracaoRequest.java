package digytal.desktop.app.model.modulo.acesso.usuario;

import lombok.Data;

@Data
public class SenhaAlteracaoRequest {
    private Integer usuario;
    private String senhaAtual;
    private String novaSenha;
    private String novaSenhaConfirmacao;
}
