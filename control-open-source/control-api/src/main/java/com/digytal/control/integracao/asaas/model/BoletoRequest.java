package com.digytal.control.integracao.asaas.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BoletoRequest {
    private String customer;
    private String billingType = "BOLETO";
    private String dueDate;
    private Double value;
    private String description;
    private String externalReference;
}
