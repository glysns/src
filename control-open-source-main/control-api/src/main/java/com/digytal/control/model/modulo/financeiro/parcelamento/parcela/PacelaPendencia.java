package com.digytal.control.model.modulo.financeiro.parcelamento.parcela;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Data
public class PacelaPendencia {
    @Column(name = "pend_is_atrasada")
    private boolean atrasada;
    @Column(name = "pend_dias_atraso")
    private Integer diasAtraso=0;
    @Column(name = "pend_is_negociada")
    private boolean negociada;
    @Column(name = "pend_dt_prox_vencto")
    private LocalDate dataProximoVencimento;
}
