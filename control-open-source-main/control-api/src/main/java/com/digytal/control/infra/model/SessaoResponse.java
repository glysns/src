package com.digytal.control.infra.model;

import com.digytal.control.infra.model.usuario.UsuarioResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SessaoResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inicioSessao;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fimSessao;
    private UsuarioResponse usuario;
    private String token;
}
