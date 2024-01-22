package springdatajpawebapi.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_profissao")
public class Profissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Embedded
    private ProfissaoLog log;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @PrePersist
    private void logInclusao(){
        System.out.println("chamando este método quando o Hibernate for salvar este objeto");
        this.log = new ProfissaoLog();
        this.log.setDataHoraCriacao(LocalDateTime.now());
    }
    @PreUpdate
    private void logAlteracao(){
        System.out.println("chamando este método quando o Hibernate for alterar este objeto");
        this.log.setDataHoraAlteracao(LocalDateTime.now());
    }
}
