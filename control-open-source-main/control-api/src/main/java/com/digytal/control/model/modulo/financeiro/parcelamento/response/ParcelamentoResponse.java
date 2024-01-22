package com.digytal.control.model.modulo.financeiro.parcelamento.response;

import com.digytal.control.model.modulo.financeiro.parcelamento.ParcelamentoDetalhe;
import com.digytal.control.model.modulo.financeiro.response.LancamentoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Resposta do parcelamento",description = "Resposta referente ao parcelamento realizado")
public class ParcelamentoResponse extends LancamentoResponse {
    private ParcelamentoDetalhe detalhe = new ParcelamentoDetalhe();
}
