package com.digytal.control.model.modulo.acesso.organizacao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "apl_acesso", name = "tab_organizacao")
@Data
public class OrganizacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    private String cpfCnpj;
    private String nome;
    private String email;
}
