package com.digytal.control.model.modulo.contrato;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContratoCalculoItem {
    private Double itensTotalPrevisto=0.0;
    private Double itensTotalAplicado=0.0;
    private Double itensTotalAcrescimo=0.0;
    private Double itensTotalDesconto=0.0;
    private List<ContratoItemEntity> itens = new ArrayList<>();
}
