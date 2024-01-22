package com.digytal.control.model.modulo.cadastro.produto.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(name = "Restaposta do modelo",description = "Resposta referente ao modelo requerido")
public class ModeloResponse extends ModeloRequest{
    @Schema(description = "identificador único da respata do modelo")
    private Integer id;
}
