package com.digytal.control.model.modulo.acesso.empresa.pagamento;

import com.digytal.control.model.comum.MeioPagamento;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "apl_acesso", name = "tab_forma_pagamento")
@Data
public class FormaPagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    @Column(name = "empresa_id")
    private Integer empresa;
    @Column(name = "nr_parcelas")
    private Integer numeroParcelas;
    @Column(name = "meio_pagto")
    private MeioPagamento meioPagamento;
    @Column(name = "taxa")
    private Double taxa;
    @Column(name = "conta_id")
    private Integer conta;
}
