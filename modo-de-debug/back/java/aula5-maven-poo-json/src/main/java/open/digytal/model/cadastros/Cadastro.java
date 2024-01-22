package open.digytal.model.cadastros;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Cadastro {
    private String nome;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    //@JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataNascimento;
    private String cpf;
    private Endereco endereco;
    private Sexo sexo;
    @JsonProperty("estado_civil")
    private EstadoCivil estadoCivil;
    private Telefone telefone;

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }



}
