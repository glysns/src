package com.digytal.control.model.modulo.financeiro.parcelamento.parcela;

import com.digytal.control.model.modulo.financeiro.parcelamento.ParcelamentoDetalhe;
import com.digytal.control.model.modulo.financeiro.parcelamento.Quitacao;
import com.digytal.control.model.modulo.financeiro.parcelamento.boleto.ParcelaBoleto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "apl_financeiro", name = "tab_parcelamento_parcela")
@Data
public class ParcelaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String descricao;
    @Embedded
    private ParcelamentoDetalhe detalhe = new ParcelamentoDetalhe();
    @Embedded
    private ParcelaAliquota aliquota = new ParcelaAliquota();
    @Embedded
    private PacelaPendencia pendencia = new PacelaPendencia();
    @Embedded
    private Quitacao quitacao = new Quitacao();
    @Embedded
    private ParcelaBoleto boleto = new ParcelaBoleto();
    @Column(name = "parc_lancto_id", insertable = false, updatable = false)
    private Integer parcelamento;
    private String observacao;

}
