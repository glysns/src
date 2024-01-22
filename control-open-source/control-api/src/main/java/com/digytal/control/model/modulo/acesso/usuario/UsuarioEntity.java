package com.digytal.control.model.modulo.acesso.usuario;

import lombok.Data;

import lombok.AccessLevel;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(schema = "apl_acesso", name = "tab_usuario")
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "is_expirado")
    private boolean expirado;

    @Column(name = "is_bloqueado")
    private boolean bloqueado;

    @Column(name = "is_consultor")
    private boolean consultor;

    @Column(name = "senha")
    private String senha;

    @Column(name = "documento")
    private String documento;

    @Column(name = "cadastro_id")
    private Integer cadastro;

    @ElementCollection
    @CollectionTable(schema = "apl_acesso", name="tab_usuario_empresa",
            joinColumns=@JoinColumn(name = "usuario_id", referencedColumnName = "id"))
    @Column(name="empresa_id")
    private List<Integer> empresas;
}
