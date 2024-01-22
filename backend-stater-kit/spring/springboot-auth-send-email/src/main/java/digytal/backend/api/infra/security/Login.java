package digytal.backend.api.infra.security;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name="Login", description="Representacao dos dados de login")
@Data
public class Login {
    @Schema(description="login ou E-mail do usuário cadastrado",example = "gleyson")
    private String username;
    @Schema(description="Senha do usuário cadastrado",example = "Jwt@123")
    private String password;
}
