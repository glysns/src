package com.digytal.control.model.modulo.financeiro.transacao.pagamento;

import com.digytal.control.model.comum.MeioPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Requisição de forma pagamento rateio ", description = "Pré requisito para rateio de pagamento")
public class FormaPagamentoRequest {
    @Schema(description = "meio de pagamento",subTypes = MeioPagamento.class, example = "DEBITO",requiredMode = Schema.RequiredMode.REQUIRED)
    private MeioPagamento meioPagamento;
    @Schema(description = "valor original do pagamento rateio",type = "numeric", example = "100.0",requiredMode = Schema.RequiredMode.REQUIRED)
    private Double valorOriginal;
    @Schema(description = "Taxa do pagamento",type = "numeric",example = "0.5",requiredMode = Schema.RequiredMode.REQUIRED)
    private Double taxaPagamento;
    @Schema(description = "Valor pago considerando calculo de taxa de pagamento", example = "105.0", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double valorPago;
    @Schema(description = "forma de pagamento das parcelas",subTypes = FormaParcelamentoRequest.class,requiredMode = Schema.RequiredMode.AUTO)
    private FormaParcelamentoRequest parcelamento;

}
