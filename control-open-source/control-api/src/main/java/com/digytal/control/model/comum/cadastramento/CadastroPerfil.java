package com.digytal.control.model.comum.cadastramento;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class CadastroPerfil {
    @Schema(description="esse cadsatro é para um cliente", example = "true", allowableValues = {"true","false"})
    @Column(name = "is_cliente")
    private boolean cliente;
    @Schema(description="esse cadastro de um fornecedor", example = "false", allowableValues = {"true","false"})
    @Column(name = "is_fornecedor")
    private boolean fornecedor;
}
