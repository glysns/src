package digytal.backend.api.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioNovaSenha {
    @Schema(description="E-mail", example = "iza.belly@hotmail.com")
    private String email;

    @Schema(description="Senha atual", example = "OldPass")
    private String senhaAtual;

    @Schema(description="Senha nova", example = "NewPass")
    private String senhaNova;
    @Schema(description="Senha nova confirmacao", example = "NewPass")
    private String senhaNovaConfirmacao;
}
