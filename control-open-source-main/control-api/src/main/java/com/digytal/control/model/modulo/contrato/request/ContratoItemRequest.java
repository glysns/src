package com.digytal.control.model.modulo.contrato.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição dos itens de contrato", description = "Pré requisitos para o preenchimento do(s) iten(s) do contrato")
public class ContratoItemRequest {
    @Schema(description = "identificador único do produto", type = "numeric", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer produto;
    @Schema(description = "descrição do produto", type = "characters",minLength = 6,maxLength = 250, example = "NOTEBOOK ACCER EXPIRIAN,PROCESSADOR i7 10º GERAÇÃO",requiredMode = Schema.RequiredMode.REQUIRED)
    private String descricao;
    @Schema(description = "quantidade de produto(s)", type = "numeric", example = "12", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double quantidade;
    @Schema(description = "valor unitário do produto", type = "numeric", example = "2684.9",requiredMode = Schema.RequiredMode.REQUIRED)
    private Double valorUnitario;
    @Schema(description = "valor aplicado no(s) produto(s)", type = "numeric", example = "32268.0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Double valorAplicado;
}
