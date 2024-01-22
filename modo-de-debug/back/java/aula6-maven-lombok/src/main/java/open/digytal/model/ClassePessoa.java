package open.digytal.model;

import java.time.LocalDate;

//Convencão JavaBeans
public class ClassePessoa {
    //atributos privados
    private LocalDate dataNascimento; //poderia ser LocalDateTime
    private String sexo;
    private String nome;

    //possua um construtor sem argumentos;
    //construtor default é que existe na classe
    //quando não definimos o construtor sem argumentos
    public ClassePessoa(){

    }
    //construtor com argumentos com base EM todos os atributos da classe
    //vai com calma blzinha !!, nem sempre este será o melhor atalho
    public ClassePessoa(LocalDate dataNascimento, String sexo, String nome) {
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.nome = nome;
    }
    // getters e setteres
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
