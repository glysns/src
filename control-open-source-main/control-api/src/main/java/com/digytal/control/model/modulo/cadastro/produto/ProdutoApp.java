package com.digytal.control.model.modulo.cadastro.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@Schema(name = "Requisição do app", description = "Pré requisitos para o validar o status app")
public class ProdutoApp {
    @Column(name = "app_is_visivel")
    @Schema(description = "verifica o statuso de vizualização do aplicaltivo", type = "boolean",example = "false", allowableValues = {"true","false"})
    private boolean visivel;
    @Column(name = "app_ordem_visualizacao")
    @Schema(description = "ondem de vizualização do aplicaltivo", type = "integer",example = "0")
    private int ordemVisualizacao=0;
}
