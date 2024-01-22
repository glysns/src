package com.digytal.control.model.modulo.financeiro.parcelamento.boleto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Data
public class ParcelaBoleto {
    @Column(name = "bol_is_solicitado")
    private boolean solicitado;
    @Column(name = "bol_repasse_tipo")
    private String tipoRepasse;
    @Column(name = "bol_is_repasse_confirmado")
    private boolean repasseConfirmado;
    @Column(name = "bol_status")
    private ParcelaBoletoStatus status ;
    @Column(name = "bol_nr_autorizacao")
    private String numeroAutorizacao;
    @Column(name = "bol_url_impressao")
    private String urlImpressao;
    @Column(name = "bol_linha_digitavel")
    private String linhaDigitavel;
    @Column(name = "bol_vl_original")
    private Double valorOriginal;
    @Column(name = "bol_vl_tx_impressao")
    private Double valorTaxaImpressao;
    @Column(name = "bol_vl_impresso")
    private Double valorImpresso;
    @Column(name = "bol_vl_compensado")
    private Double valorCompensado;
    @Column(name = "bol_tipo_compensacao")
    private String tipoCompensacao;
    @Column(name = "bol_dt_pagamento")
    private LocalDate dataPagamento;
    @Column(name = "bol_dt_compensacao")
    private LocalDate dataCompensacao;

}
