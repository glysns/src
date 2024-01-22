package com.digytal.control.integracao.asaas.model;

import lombok.Data;

@Data
public class PagamentoEvent {
    private String event;
    private Pagamento payment;

}
