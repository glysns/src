package com.digytal.control.model.comum.cadastramento;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class CadastroIntegracao {
    @Column(name = "integra_asaas_id")
    private String asaas;
}
