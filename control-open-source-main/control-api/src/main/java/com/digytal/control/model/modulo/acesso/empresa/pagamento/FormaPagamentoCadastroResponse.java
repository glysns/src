package com.digytal.control.model.modulo.acesso.empresa.pagamento;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Resposta referente a forma  de pagamento")
public class FormaPagamentoCadastroResponse extends FormaPagamentoCadastroRequest {
    @Schema(description = "identificador único da forma de pagamento")
    private Integer id;
    @Schema(description = "identificador da empresa")
    private Integer empresa;
    @Schema(description = "numero da conta")
    private Integer conta;
}
