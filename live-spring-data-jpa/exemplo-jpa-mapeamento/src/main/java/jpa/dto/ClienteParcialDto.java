package jpa.dto;

public class ClienteParcialDto {
    private String nome;
    private String logradouro;
    public ClienteParcialDto(String nome, String logradouro) {
        this.nome = nome;
        this.logradouro = logradouro;
    }
    public String getNome() {
        return nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    @Override
    public String toString() {
        return "ClienteParcial{" +
                "nome='" + nome + '\'' +
                ", logradouro='" + logradouro + '\'' +
                '}';
    }
}
