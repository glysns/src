package com.digytal.control.model.modulo.cadastro.produto.categoria;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição da categoria",description = "Pré requisitos referente a categoria")
  public class CategoriaRequest{
    @Schema(description = "nome da categoria",type = "string",minLength = 2,maxLength = 30,example = "ELETRONICOS",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;
    @Schema(description= "sigla da marca",type = "string",minLength = 2,maxLength = 6,example = "ELTROS")
    private String sigla;
    @Schema(description = "nome abrevidado da categoria",type = "string",maxLength = 20,example = "ELETRONICOS")
    private String nomeAbreviado;
}

