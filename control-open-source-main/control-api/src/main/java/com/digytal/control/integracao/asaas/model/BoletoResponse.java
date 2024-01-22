package com.digytal.control.integracao.asaas.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoletoResponse extends BoletoRequest{
    private String bankSlipUrl;
    private String status;
    private String id;
    private String nossoNumero;
    private boolean deleted;
    private Double netValue;
    private String billingType;
    //data de pagamento
    private LocalDate clientPaymentDate;
    //data de compensacao
    private LocalDate paymentDate;
}
