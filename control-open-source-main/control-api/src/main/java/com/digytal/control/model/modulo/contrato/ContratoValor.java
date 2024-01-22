package com.digytal.control.model.modulo.contrato;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class ContratoValor {
    @Column(name = "vl_previsto")
    private Double previsto;
    @Column(name = "vl_aplicado")
    private Double aplicado;
    @Column(name = "vl_acres_itens")
    private Double acrescimoItens;
    @Column(name = "vl_acres_pagto")
    private Double acrescimoPagamento;
    @Column(name = "vl_desc_itens")
    private Double descontoItens;
    @Column(name = "vl_desc_manual")
    private Double descontoManual;

}
