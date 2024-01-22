package com.digytal.control.model.comum.cadastramento;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição do cadastro simplificado ")
public class CadastroSimplificadoRequest {
    @Schema(description="primeiro nome ou nome fantasia", maxLength = 50,example = "LOJAS E ACESSORIOS PRODUTOS IMPORTADOS",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nomeFantasia;

    @Schema(description="sobrenome ou razão social", maxLength = 50,example = "J & R IMPORTADOS LTDA")
    private String sobrenomeSocial;

    @Schema(description="conta de e-mail da empresa", maxLength = 70,example = "contato@jrimportados.com")
    private String email;
}
