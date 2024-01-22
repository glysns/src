package com.digytal.control.model.modulo.param.cep;

public enum Uf {
    AC("Acre"),
    AL("Alagoas"),
    AP("Amapa"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceara"),
    ES("Espirito Santo"),
    GO("Goias"),
    MA("Maranhao"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Para"),
    PB("Paraiba"),
    PR("Parana"),
    PE("Pernambuco"),
    PI("Piaui"),
    RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"),
    RS("Rio Grande do Sul"),
    RO("Rondonia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SP("Sao Paulo"),
    SE("Sergipe"),
    DF("Distrito Federal"),
    BR("Brasil"),
    EX("Exterior"),
    ;
    private String nome;
    private Uf(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
