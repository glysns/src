package com.digytal.control.model.modulo.acesso.empresa.aplicacao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "apl_acesso", name = "tab_aplicacao")
@Data
public class AplicacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String nome;
    private String localiza;
    private AplicacaoTipo tipo;
    @Column(name = "is_area")
    private boolean area;
    @Column(name = "is_natureza")
    private boolean natureza;
    @Column(name = "is_principal")
    private boolean principal;
    @Column(name = "organizacao_id")
    private Integer organizacao;
}
