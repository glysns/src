package com.digytal.control.model.modulo.acesso.empresa.aplicacao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Resposta da aplicação")
public class AplicacaoResponse extends AplicacaoRequest {
    @Schema(description = "identificador da resposta")
    private Integer id;
    @Schema(description = "tipo de resposta",subTypes = AplicacaoTipo.class)
    private AplicacaoTipo tipo;
}
