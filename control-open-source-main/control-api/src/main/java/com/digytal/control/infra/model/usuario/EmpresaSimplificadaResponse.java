package com.digytal.control.infra.model.usuario;

import lombok.Data;

@Data
public class EmpresaSimplificadaResponse {
    private Integer id;
    private String cpfCnpj;
    private String nomeFantasia;
    private String sobrenomeSocial;
    private Integer organizacao;
    private boolean padrao;
}
