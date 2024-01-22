package com.digytal.control.model.modulo.cadastro.produto.categoria;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(schema = "apl_cadastro", name = "tab_categoria")
public class CategoriaEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sigla;
    private String localiza;
    @Column(name = "nome_abreviado")
    private String nomeAbreviado;
    @Column(name = "is_excluido")
    private boolean excluido;
    @Column(name = "organizacao_id")
    private Integer organizacao;
    public CategoriaEntity(){}
    public CategoriaEntity(Integer organizacao){this.organizacao = organizacao;}

}
