package com.digytal.control.model.modulo.financeiro.parcelamento.parcela.liquidacao;

import com.digytal.control.model.comum.MeioPagamento;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "apl_financeiro", name = "tab_parcelamento_parcela_pagto")
@Data
public class ParcelaPagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private Double valor;
    @Column(name = "dt_pagto")
    private LocalDate data;
    @Column(name="conta_banco_id")
    private Integer contaBanco;
    @Column(name="parcela_id")
    private Integer parcela;
    @Column(name="parcelamento_id")
    private Integer parcelamento;
    @Column(name="cpt_competencia")
    private Integer competencia;
    @Column(name = "meio_pagto")
    private MeioPagamento meioPagamento;
    @Column(name = "bol_nr_autorizacao")
    private String boletoNumeroAutorizacao;
    @Column(name = "bol_vl_original")
    private Double boletoValorOriginal;
    @Column(name = "usuario_id")
    private Integer usuario;
}
