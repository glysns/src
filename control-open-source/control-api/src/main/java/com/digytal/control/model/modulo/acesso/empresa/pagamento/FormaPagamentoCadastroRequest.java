package com.digytal.control.model.modulo.acesso.empresa.pagamento;

import com.digytal.control.model.comum.MeioPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição do pagamento",description = "Pré requisistos da forma de pagamento")
public class FormaPagamentoCadastroRequest {
    @Schema(description= "meio de pagamento",subTypes = MeioPagamento.class,requiredMode = Schema.RequiredMode.REQUIRED,example = "DEBIDO")
    private MeioPagamento meioPagamento;
    @Schema(description = "número de parcelas",type = "numeric",minLength = 2,example = "4")
    private Integer numeroParcelas;
    @Schema(description = "taxa de pagamento",type = "numeric ",example = "2.3")
    private Double taxa;
}
