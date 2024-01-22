package com.digytal.control.model.modulo.contrato;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Data
public class ContratoVigencia {
    @Column(name = "vigencia_inicio")
    private LocalDateTime inicio;
    @Column(name = "vigencia_fim")
    private LocalDateTime fim;
}
