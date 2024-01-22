package com.digytal.control.model.modulo.cadastro.produto.modelo;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
@Entity
@Data
@Table(schema = "apl_cadastro", name = "tab_modelo")
public class ModeloEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String nome;
    private String localiza;
    @Column(name = "nome_abreviado")
    private String nomeAbreviado;
    private String sigla;
    @Column(name = "is_excluido")
    private boolean excluido;
    @Column(name = "organizacao_id")
    private Integer organizacao;
    public ModeloEntity(){}
    public ModeloEntity(Integer organizacao){ this.organizacao = organizacao;}
}
