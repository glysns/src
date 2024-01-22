package com.digytal.control.model.modulo.financeiro.parcelamento;

import com.digytal.control.model.modulo.financeiro.Movimento;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.ParcelaEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "apl_financeiro", name = "tab_parcelamento")
@Data
public class ParcelamentoEntity extends Movimento {
    @Embedded
    private ParcelamentoDetalhe detalhe;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parc_lancto_id")
    private List<ParcelaEntity> parcelas;
}
