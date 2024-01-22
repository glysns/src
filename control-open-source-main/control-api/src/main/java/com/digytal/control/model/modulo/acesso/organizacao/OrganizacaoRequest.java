package com.digytal.control.model.modulo.acesso.organizacao;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição da organização",description="Estrutura das requisições de inclusão e alteração relacionadas as organizações do sistema")
public class OrganizacaoRequest {
    @Schema(description="nome da organização",minLength = 4, maxLength = 70,requiredMode = Schema.RequiredMode.REQUIRED,example = "GRUPO ATACADISTA LTDA")
    private String nome;
}
