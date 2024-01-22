package com.digytal.control.model.modulo.contrato.request;

import com.digytal.control.model.modulo.financeiro.transacao.pagamento.FormaPagamentoRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(name = "Requisição do contrato", description = "Pré requisito para o preenchimento do contrato")
public class ContratoRequest {
    @Schema(description = "data do contrato", type = "Date", example = "2023-10-09", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate data;
    @Schema(description = "descrição do contrato", type = "characters", example = "CONTRATO PARTICULAR DE VENDA DE PRODUTOS", requiredMode = Schema.RequiredMode.REQUIRED)
    private String descricao;
    @Schema(description = "identificador do cadastro", type = "numeric", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer cadastro;
    //negociador, vendedor
    @Schema(description = "identificador do intermediador do contrato", type = "numeric", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer intermediador;
    @Schema(description = "valor aplicado", type = "numeric", example = "4568.5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double valorAplicado;
    @Schema(description = "valor do desconto", type = "numeric", example = "245.2")
    private Double valorDescontoManual;
    @Schema(description = "item(ns) do contrato",subTypes = ContratoItemRequest.class)
    private List<ContratoItemRequest> itens;
    @Schema(description="formas ou rateio de pagamento",subTypes = FormaPagamentoRequest.class)
    private List<FormaPagamentoRequest> formasPagamento;
}
