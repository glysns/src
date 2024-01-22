package com.digytal.control.model.modulo.cadastro.produto.unidademedida;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição de Undiades de Medida", description = "Requisição de inclusão e alteração de unidades de medida")
public class UnidadeMedidaRequest {
    @Schema(description = "nome da unidade de medida",type = "string", minLength = 2,maxLength = 30,example = "QUILOGRAMANA",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nome;
    @Schema(description = "sigla da unidade de medida, muitas das vezes pode ser identifco ao id",type = "string", minLength = 2,maxLength = 6,example = "KG", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sigla;
    @Schema(description = "descrição da unidade de medida",type = "string", minLength = 2,maxLength = 80,example = "Unidade de medida utilizada em produtos com grandeza de unidade tipo massa")
    private String descricao;
    @Schema(description = "conteúdo inserido na unidade de medida, padrão 1",type = "double", example = "1.0")
    private Double conteudo;
    @Schema(description = "determina se a unidade de medida será considerada embalagem",type = "boolean", example = "true", allowableValues = {"true","false"},requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean embalagem;
}
