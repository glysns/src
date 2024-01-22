package com.digytal.control.model.modulo.cadastro.produto;

import com.digytal.control.model.comum.Associacao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(name = "Resposta do produto",description = "Resposta do produto requerido")
public class ProdutoResponse extends Produto {
    @Schema(description = "identificador único do produto response")
    private Integer id;
    @Schema(description = "saldo do produto")
    private Double saldo;
    private Associacao marca;
    private Associacao modelo;
    private Associacao categoria;
    private Associacao unidadeMedida;

}
