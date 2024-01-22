package com.digytal.control.model.modulo.acesso.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name="Requisição para nova senha", description="Pré requisitos para criação de uma nova senha")
public class SenhaAlteracaoRequest {
    @Schema(description="identificador único do usuário", maxLength = 50,requiredMode = Schema.RequiredMode.REQUIRED,example = "1")
    private Integer usuario;
    @Schema(description="senha utilizada até o presente momento", minLength = 8,requiredMode = Schema.RequiredMode.REQUIRED,example = "StronP@ss")
    private String senhaAtual;
    @Schema(description="nova senha de acordo com a política segurança", minLength = 8,requiredMode = Schema.RequiredMode.REQUIRED,example = "NewStronP@ss")
    private String novaSenha;
    @Schema(description="repetição da nova senha para confirmação", minLength = 8,requiredMode = Schema.RequiredMode.REQUIRED  ,example = "NewStronP@ss")
    private String novaSenhaConfirmacao;
}
