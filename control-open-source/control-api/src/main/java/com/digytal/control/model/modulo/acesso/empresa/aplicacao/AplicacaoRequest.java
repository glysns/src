package com.digytal.control.model.modulo.acesso.empresa.aplicacao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(name = "Requisição da aplicação",description = "Pré requisito para aplicação")
public class AplicacaoRequest {
    @Schema(description = "nome da aplicação", type = "characters",requiredMode = Schema.RequiredMode.REQUIRED, example = "RECEITA")
    private String nome;

}
