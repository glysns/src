package com.digytal.control.model.modulo.acesso.organizacao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name="Resposta referente a organização", description="Estrutura das organizações do sistema")
public class OrganizacaoResponse extends OrganizacaoRequest{
    @Schema(description="identificador único da organização")
    private Integer id;
}
