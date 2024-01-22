package com.digytal.control.model.modulo.financeiro;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Aplicacao {
    @Column(name = "aplic_area")
    private Integer area;
    @Column(name = "aplic_natureza")
    private Integer natureza;
}
