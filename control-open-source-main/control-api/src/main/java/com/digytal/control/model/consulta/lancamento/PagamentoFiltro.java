package com.digytal.control.model.consulta.lancamento;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Filtro de pagamento", description = "Filtro para localizar pagamentos específico")
public class PagamentoFiltro extends LancamentoFiltro{
    @Schema(description = "numero da conta",type = "numeric",example = "1001728",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer conta;
}
