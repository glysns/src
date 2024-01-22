package digytal.backend.api.model.usuario;

import digytal.backend.api.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequest {
    @Schema(description="Nome do usuario", example = "GLEYSON SAMPAIO")
    private String nome;

    @Schema(description="Login", example = "izabelly")
    private String login;

    @Schema(description="Senha", example = "Jwt@123")
    private String senha;

}
