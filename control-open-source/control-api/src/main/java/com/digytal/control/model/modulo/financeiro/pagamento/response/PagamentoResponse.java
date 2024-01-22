package com.digytal.control.model.modulo.financeiro.pagamento.response;
import com.digytal.control.model.comum.Associacao;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoValor;
import com.digytal.control.model.modulo.financeiro.response.LancamentoResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Resposta do pagamento",description = "Resposta referente ao pagamento realizado")
public class PagamentoResponse extends LancamentoResponse {
    @Schema(name = "valor do pagamento",subTypes = TransacaoValor.class)
    private TransacaoValor valor;

}
