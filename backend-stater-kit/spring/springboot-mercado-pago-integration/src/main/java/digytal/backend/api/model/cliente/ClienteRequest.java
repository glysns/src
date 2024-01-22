package digytal.backend.api.model.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteRequest {
    @Schema(description="Nome do cliente", example = "GLEYSON SAMPAIO")
    private String nome;
    @Schema(description="CPF do Cliente", example = "87509811498")
    private String cpf;
    @Schema(description="Data de nascimento", example = "1988-01-01")
    private LocalDate dataNascimento;
    @Schema(description="E-mail", example = "gleyson@digytal.com.br")
    private String email;

    @Override
    public String toString() {
        return "ClienteRequest{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", email='" + email + '\'' +
                '}';
    }
}
