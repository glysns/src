package com.digytal.control.model.modulo.financeiro.transacao.rateio;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@Embeddable
public class TransacaoParcelamento {
    @Column(name = "vl_parcela")
    private Double valorParcela;
    @Column(name = "num_parcelas")
    private Integer numeroParcelas;
    @Column(name = "dt_pri_vencto")
    private LocalDate dataPrimeiroVencimento;
}
