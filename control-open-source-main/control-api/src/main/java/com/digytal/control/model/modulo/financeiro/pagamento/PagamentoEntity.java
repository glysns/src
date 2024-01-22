package com.digytal.control.model.modulo.financeiro.pagamento;

import com.digytal.control.model.modulo.financeiro.transacao.TransacaoValor;
import com.digytal.control.model.modulo.financeiro.Movimento;
import lombok.Data;
import javax.persistence.*;
@Entity
@Table(schema = "apl_financeiro", name = "tab_pagamento")
@Data
public class PagamentoEntity extends Movimento {
    @Column(name = "conta_id")
    private Integer conta;
    @Embedded
    private TransacaoValor valor;
    @Column(name = "parcelamento_id")
    private Integer parcelamento;
}
