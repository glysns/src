package com.digytal.control.model.modulo.acesso.empresa.conta;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição da conta", description = "Pré requisitos para cadastrar uma conta")
public class ContaRequest {

    @Schema(description = "numero da conta",requiredMode = Schema.RequiredMode.REQUIRED,type = "character")
    private String numero;
    @Schema(description = "agencia",requiredMode = Schema.RequiredMode.REQUIRED,type = "character")
    private String agencia;
    @Schema(description = "legenda do banco",type = "character",example = "LEGENDA",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String legenda;
    @Schema(description = "identificador do banco",requiredMode = Schema.RequiredMode.REQUIRED,type = "numeric",example = "123")
    private Integer banco;
    @Schema(description = "é conta crédito",requiredMode = Schema.RequiredMode.REQUIRED,type = "boolean",allowableValues = {"true","false"})
    private boolean contaCredito;
    @Schema(description = "fatura",subTypes = ContaFatura.class)
    private ContaFatura fatura;
    @Schema(description = "chave-pix",requiredMode = Schema.RequiredMode.REQUIRED,type = "character",example = "121454755545")
    private String chavePix;
    @Schema(description = "descrição da fatura",type = "character",example = "VALOR REFERENTE AO PEDITO")
    private String descricao;
}
