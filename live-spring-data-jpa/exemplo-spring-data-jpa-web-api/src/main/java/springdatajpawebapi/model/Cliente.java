package springdatajpawebapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import springdatajpawebapi.model.cliente.Endereco;
import springdatajpawebapi.model.cliente.Sexo;
import springdatajpawebapi.model.cliente.Telefone;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tab_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "dt_nascto")
    private LocalDate dataNascimento;

    @Embedded
    private Endereco endereco;

    @ElementCollection
    @CollectionTable(name="tab_cliente_email",
            joinColumns=@JoinColumn(name = "cliente_id", referencedColumnName = "id"))
    @Column(name="email")
    private Set<String> emails;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id",referencedColumnName = "id")
    private Set<Telefone> telefones;

    /**prq não usa o objeto Profissao
    /e as anotações @ManyToOne ??
    @Column(name = "profissao_id")
    private Integer profissao;

    */

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissao_id")
    private Profissao profissao;

    @Enumerated(EnumType.STRING)//ordinal represeta a ordem o elemento do enum - CUIDADO
    private Sexo sexo;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }


}
