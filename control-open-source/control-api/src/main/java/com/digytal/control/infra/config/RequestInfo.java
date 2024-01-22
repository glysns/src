package com.digytal.control.infra.config;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@Schema(name = "Requisição informativa")
public class RequestInfo {
    @Schema(description = "identificador único do usuário", type = "numeric")
    private Integer usuario;
    @Schema(description = "identificador único da empresa", type = "numeric")
    private Integer empresa;
    @Schema(description = "identificador único da organização", type = "numeric")
    private Integer organizacao;
}
