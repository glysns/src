package com.digytal.control.model.modulo.financeiro.transacao;

import com.digytal.control.model.modulo.financeiro.transacao.pagamento.FormaPagamentoRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(name = "Requisição de lancamentos",description = "Pré requisitos para lançameto")
public class TransacaoRequest extends Transacao {
    @Schema(description="data do lançamento",type = "date",example = "2023-01-01")
    private LocalDate data;
    @Schema(description="valor informado",type = "numeric",requiredMode = Schema.RequiredMode.REQUIRED,example = "356.14")
    private Double valor;
    @Schema(description="Área, departamento ou setor",type = "numeric",example = "1")
    private Integer area;
    @Schema(description="idetificador único da natureza do lançamento",type = "numeric",example = "1")
    private Integer natureza;
    @Schema(description="identificador único do cadastro que originou o registro",type = "numeric", example = "1")
    private Integer cadastro;
    @Schema(description="forma ou rateio de pagamento")
    private List<FormaPagamentoRequest> formasPagamento;
}
