package com.digytal.control.model.modulo.financeiro.transacao.rateio;

import com.digytal.control.model.comum.MeioPagamento;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "apl_financeiro", name = "tab_transacao_rateio")
@Data
public class TransacaoRateioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    @Column(name = "meio_pagto")
    private MeioPagamento meioPagamento;
    @Column(name = "vl_original")
    private Double valorOriginal;
    @Column(name = "tx_meio_pagto")
    private Double taxaPagamento;
    @Column(name = "vl_pago")
    private Double valorPago;
    @Embedded
    private TransacaoParcelamento parcelamento = new TransacaoParcelamento();
}
