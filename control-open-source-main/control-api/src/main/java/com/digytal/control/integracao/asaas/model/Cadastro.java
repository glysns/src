package com.digytal.control.integracao.asaas.model;

import lombok.Data;

@Data
public class Cadastro {
    private String id;
    private String name;
    private String email;
    private String cpfCnpj;
    private boolean deleted;
}
