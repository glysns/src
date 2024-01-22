package com.digytal.control.model.modulo.cadastro.produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(name = "Requisição do produto",description = "Pré requisitos referente ao produto")
public class ProdutoRequest extends Produto {

    @Schema(description = "unidade embalagem" ,type ="int", example = "1")
    private Integer unidadeEmbalagem;
    @Schema(description = "unidade medida", type = "int",example = "1",requiredMode= Schema.RequiredMode.REQUIRED)
    private Integer unidadeMedida;;

    @Schema(type = "integer",example = "1",description = "identificador relacionado a marca do produto")
    private Integer marca;
    @Schema(type = "integer",example = "1",description = "Identificador relacionado ao modelo do produto")
    private Integer modelo;
    @Schema(type = "integer",example = "1",description = "Identificador relacionado a categoria do produto")
    private Integer categoria;

}
