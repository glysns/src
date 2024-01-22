package com.digytal.control.model.comum;


import com.digytal.control.model.comum.telefone.Telefone;
import com.digytal.control.model.comum.endereco.Endereco;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class EntidadeCadastral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;
    //private String localiza;
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @Column(name = "sobrenome_social")
    private String sobrenomeSocial;
    @Column(name = "rg_ie")
    private String rgIe;
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;
    @Column(name = "email")
    private String email;
    @Embedded
    private Endereco endereco = new Endereco();
    @Embedded
    private Telefone telefone = new Telefone();
    @Column(name = "dt_aniversario")
    private LocalDate aniversario;
    @Column(name = "ativ_com_prof")
    private String atividadeComecialProfissional="";
    @Column(name = "organizacao_id")
    protected Integer organizacao;
    @Column(name = "is_incompleto")
    private boolean incompleto;
}
