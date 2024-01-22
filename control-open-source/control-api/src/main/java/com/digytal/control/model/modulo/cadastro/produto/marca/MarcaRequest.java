package com.digytal.control.model.modulo.cadastro.produto.marca;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(name = "Requisição da marca",description = "Pré requisistos referente a marca")
public class MarcaRequest {
    @Schema(description = "nome da marca",type = "string",minLength = 2,maxLength = 30,example = "CHEVROLET",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;
    @Schema(description= "sigla da marca",type = "string",minLength = 2,maxLength = 6,example = "CHE")
    private String sigla;
    @Schema(description = "nome abrevidado da marca",type = "string",maxLength = 20,example = "CHEVROLET")
    private String nomeAbreviado;
}
