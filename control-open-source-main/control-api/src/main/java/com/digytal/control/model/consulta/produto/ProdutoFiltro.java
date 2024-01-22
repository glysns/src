package com.digytal.control.model.consulta.produto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Filtro de produtos", description = "Filtrar produtos do sistema")
public class ProdutoFiltro {
    private String nome;
    private Integer categoria;
    private Integer marca;
    private Integer modelo;
}
