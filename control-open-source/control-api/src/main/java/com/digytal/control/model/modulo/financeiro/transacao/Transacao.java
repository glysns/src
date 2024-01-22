package com.digytal.control.model.modulo.financeiro.transacao;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;

@Data
@Schema(name = "Transação",description = "Transação de pagamentos, recebimentos e parcelamentos")
public class Transacao {
    @Schema( description = "numero do documento",type = "string",example = "089660")
    private String numeroDocumento;
    @Schema(description="título do lançamento",type = "string",example = "Receitas Diversas")
    private String titulo;
    @Schema(description="descrição do lançamento",type = "string",requiredMode = Schema.RequiredMode.REQUIRED,example = "Receitas Diversas sobre Venda ou Serviço")
    private String descricao;
    @Schema(description="Observações adicionais",type = "string", requiredMode = Schema.RequiredMode.REQUIRED, example = "Receitas Diversas sobre Venda ou Serviço")
    private String observacao;
}
