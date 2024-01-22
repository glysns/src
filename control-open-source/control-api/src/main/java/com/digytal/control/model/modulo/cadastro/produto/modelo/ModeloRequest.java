package com.digytal.control.model.modulo.cadastro.produto.modelo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(name = "Requisição do modelo",description = "Pré requisitos referente ao modelo")
public class ModeloRequest {
    @Schema(description = "nome do modelo",type = "string",minLength = 2,maxLength = 30,example = "VECTRA HATCH",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;
    @Schema(description = "sigla do modelo",type = "string",minLength = 2,maxLength = 6,example = "VCH")
    private String sigla;
    @Schema(description = "nome abrevidado do modelo",type = "string",maxLength = 20,example = "VECTRA")
    private String nomeAbreviado;
}
