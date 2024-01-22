package digytal.backend.api.model.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioRequest {
    @Schema(description="Nome do usuario", example = "IZABELLY")
    private String nome;

    @Schema(description="Login", example = "izabelly")
    private String login;

    @Schema(description="E-mail", example = "iza.belly@hotmail.com")
    private String email;

}
