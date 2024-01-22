package com.digytal.control.model.modulo.cadastro.produto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "apl_cadastro", name = "tab_produto")
@Data
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String nome;
    private String localiza;
    private String descricao;
    @Column(name = "is_principal")
    private boolean principal;
    @Column(name = "nome_abreviado")
    private String nomeAbreviado;
    @Column(name = "unid_embalagem_id")
    private Integer unidadeEmbalagem;
    @Column(name = "unid_medida_id")
    private Integer unidadeMedida;
    @Column(name = "unid_medida_sigla")
    private String unidadeMedidaSigla;
    @Column(name = "cod_barras")
    private String codigoBarras;
    private String sku;
    @Column(name = "vl_produto")
    private Double valor;
    @Column(name = "tx_liquidacao")
    private Double taxaLiquidacao;
    @Column(name = "is_excluido")
    private boolean excluido;
    @Column(name = "is_servico")
    private boolean servico;
    @Column(name = "organizacao_id")
    private Integer organizacao;
    @Column(name = "marca_id")
    private Integer marca;
    @Column(name = "modelo_id")
    private Integer modelo;
    private Double saldo;
    @Column(name = "is_atualiza_saldo")
    private boolean atualizaSaldo;
    @Column(name = "is_interno")
    private boolean interno;
    @Column(name = "categoria_id")
    private Integer categoria;
    @Embedded
    private ProdutoApp app = new ProdutoApp();
}
