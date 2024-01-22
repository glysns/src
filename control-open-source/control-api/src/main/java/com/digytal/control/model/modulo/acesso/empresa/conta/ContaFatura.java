package com.digytal.control.model.modulo.acesso.empresa.conta;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@Schema(name = "Fatura da conta")
public class ContaFatura {
    @Column(name = "dias_intervalo")
    @Schema(description = "intervalos de dia(s)", type = "numeric", example = "5",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer diasIntervalo;
    @Column(name = "dia_vencimento")
    @Schema(description = "dia do vencimento da fatura", type = "numeric", example = "10",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer diaVencimento;
}
