package springdatajpa.dto;

import java.time.LocalDate;

public class ClienteDto {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String profissao;
    private String enderecoCompleto;

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    @Override
    public String toString() {
        return "ClienteDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", profissao='" + profissao + '\'' +
                ", enderecoCompleto='" + enderecoCompleto + '\'' +
                '}';
    }
}
