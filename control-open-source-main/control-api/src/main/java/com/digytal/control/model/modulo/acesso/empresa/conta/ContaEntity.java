package com.digytal.control.model.modulo.acesso.empresa.conta;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(schema = "apl_acesso", name = "tab_conta")
@Data
public class ContaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String numero;
    private String agencia;
    private String descricao;
    @Column(name = "empresa_id")
    private Integer empresa;
    private String legenda;
    private Double saldo;
    @Column(name = "banco_id")
    private Integer banco;
    @Column(name = "is_conta_credito")
    private boolean contaCredito;
    @Embedded
    private ContaFatura fatura;
    @Column(name = "chave_pix")
    private String chavePix;

}

