package com.digytal.control.model.comum.recorrencia;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Recorrencia {
    private Periodicidade periodicidade;
    private Integer repeticoesTotal;
    private Integer repeticoesRealizadas;
    private LocalDateTime dataProximaOcorrencia;
}
