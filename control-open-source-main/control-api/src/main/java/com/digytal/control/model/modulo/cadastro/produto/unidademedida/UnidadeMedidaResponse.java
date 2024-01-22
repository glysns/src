package com.digytal.control.model.modulo.cadastro.produto.unidademedida;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UnidadeMedidaResponse extends UnidadeMedidaRequest{
    @Schema(description = "id da unidade de medida",type = "numeric")
    private Integer id;
}
