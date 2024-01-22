package com.digytal.control.infra.model.usuario;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioEmpresaResponse extends UsuarioResponse {
    private boolean consultor;
    private List<EmpresaSimplificadaResponse> empresas;
}
