package com.digytal.control.model.modulo.financeiro.transacao.pagamento;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "Requisição de pagamento parcelado ", description = "Pré requisitos para pagamento de parcelas")
public class FormaParcelamentoRequest {
    @Schema(description = "numero de parcelas",type = "numeric",requiredMode = Schema.RequiredMode.REQUIRED,minLength = 2,example = "8")
    private Integer numeroParcelas;
    @Schema(description = "data do peimeiro vencimeno",type = "Date",requiredMode = Schema.RequiredMode.REQUIRED, example = "2023-10-29")
    private LocalDate dataPrimeiroVencimento;
}
