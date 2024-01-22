
package com.digytal.control.model.modulo.cadastro.produto.marca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
@Entity
@Data
@Table(schema = "apl_cadastro", name = "tab_marca")
public class MarcaEntity{
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
    public MarcaEntity(){

    }
    public MarcaEntity(Integer organizacao){
        this.organizacao = organizacao;
    }
}
