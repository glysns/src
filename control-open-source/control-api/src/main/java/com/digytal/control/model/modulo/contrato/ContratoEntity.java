package com.digytal.control.model.modulo.contrato;

import com.digytal.control.model.comum.Participante;
import com.digytal.control.model.comum.RegistroData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "apl_contrato", name = "tab_contrato")
@Data
public class ContratoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private Integer id;
    @Column(name = "nr_contrato")
    private String numero;
    private String descricao;
    @Column(name = "tipo")
    private ContratoTipo tipo;
    @Embedded
    private RegistroData data;
    @Column(name = "sit_contrato")
    private ContratoSituacao situacao;
    @Embedded
    private ContratoValor valor;
    @Embedded
    private Participante partes;
    @Column(name = "user_intermediador_id")
    private Integer intermediador;
    @Embedded
    private ContratoVigencia vigencia;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contrato_id")
    private List<ContratoItemEntity> itens = new ArrayList<>();
}
