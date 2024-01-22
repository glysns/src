package com.digytal.control.model.modulo.cadastro.produto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Resposta da categoria",description = "Resposta referente a categoria")
public class CategoriaResponse extends CategoriaRequest{
    @Schema(description = "identificador único da resposta de categoria")
    private Integer id;
}
