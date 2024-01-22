package com.digytal.control.model.modulo.cadastro.produto.marca;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Evandro
 */
@Data
@Schema(name = "Resposta da marca",description = "Resposta referente a marca requerida")
public class MarcaResponse extends MarcaRequest{
    @Schema(description = "identificador único da respata de marca")
    private Integer id;
}
