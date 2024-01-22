package com.digytal.control.model.modulo.financeiro.transacao;

import com.digytal.control.model.comum.Participante;
import com.digytal.control.model.comum.RegistroData;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.modulo.financeiro.Aplicacao;
import com.digytal.control.model.modulo.financeiro.pagamento.PagamentoEntity;
import com.digytal.control.model.modulo.financeiro.parcelamento.ParcelamentoEntity;
import com.digytal.control.model.modulo.financeiro.transacao.rateio.TransacaoRateioEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(schema = "apl_financeiro", name = "tab_transacao")
@Data
public class TransacaoEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    @Column(name = "nr_documento")
    private String numeroDocumento;
    private String titulo;
    private String descricao;
    @Column(name = "tipo")
    private AplicacaoTipo tipo;
    @Embedded
    private RegistroData data;
    @Embedded
    private TransacaoValor valor;
    @Embedded
    private Participante partes;
    @Column(name = "observacao")
    private String observacao;
    @Embedded
    private Aplicacao aplicacao;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "transacao_id")
    private List<PagamentoEntity> pagamentos = new ArrayList<>();
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "transacao_id")
    private List<ParcelamentoEntity> parcelamentos = new ArrayList<>();
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "transacao_id")
    private List<TransacaoRateioEntity> rateios = new ArrayList<>();
}
