package com.digytal.control.model.modulo.financeiro.parcelamento.parcela.liquidacao;

import com.digytal.control.model.comum.MeioPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ParcelaPagamentoRequest {
    @Schema(description="Valor recebido para pagamento", required = true,example = "208.33")
    private Double valor;
    @Schema(description="Meio de pagamento", required = true)
    private MeioPagamento meioPagamento;
}
