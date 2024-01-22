package com.digytal.control.model.modulo.cadastro.produto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class ProdutoItem {
    @Column(name = "prod_id")
    private Integer id;
    @Column(name = "prod_cod_barras")
    private String codigoBarras;
    @Column(name = "prod_und_med_sigla")
    private String unidadeMedidaSigla;
    @Column(name = "prod_tx_liquidacao")
    private Double taxaLiquidacao;
    @Column(name = "prod_preco")
    private Double preco;
    @Column(name = "prod_saldo")
    private Double saldo;

}
