package com.digytal.control.model.modulo.cadastro.produto.unidademedida;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Data
@Table(schema = "apl_cadastro", name = "tab_unid_medida")
public class UnidadeMedidaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String sigla;
    private String nome;
    private String localiza;
    private String descricao;
    private Double conteudo;
    @Column(name = "is_excluido")
    private boolean excluido;
    @Column(name = "is_embalagem")
    private boolean embalagem;
    @Column(name = "organizacao_id")
    private Integer organizacao;
    public UnidadeMedidaEntity(){}
    public UnidadeMedidaEntity(Integer organizacao){this.organizacao = organizacao;}
}
