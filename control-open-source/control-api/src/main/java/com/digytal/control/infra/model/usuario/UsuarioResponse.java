package com.digytal.control.infra.model.usuario;

import lombok.Data;

@Data
public class UsuarioResponse {
    private String login;
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private boolean bloqueado;
    private boolean expirado;
    private Integer perfil;
}
