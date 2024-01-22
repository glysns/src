package com.digytal.control.infra.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(title="Requisição para login", description="Pré requisitos para os dados de login")
@Data
public class LoginRequest {
    @Schema(description="login ou E-mail do usuário cadastrado",example = "admin@control.com.br")
    private String usuario;
    @Schema(description="senha do usuário cadastrado",example = "Master")
    private String senha;
}
