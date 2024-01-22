package com.digytal.control.integracao.asaas.model;

import lombok.Data;

@Data
public class Notificacao {
    private String id;
    private boolean enabled=true;
    private boolean emailEnabledForProvider=false;
    private boolean smsEnabledForProvider=false;
    private boolean emailEnabledForCustomer=false;
    private boolean smsEnabledForCustomer=false;
    private boolean phoneCallEnabledForCustomer=false;
    private boolean whatsappEnabledForCustomer = false;
    public Notificacao(){

    }
    public Notificacao(String id){
        this.id= id;
    }
}
