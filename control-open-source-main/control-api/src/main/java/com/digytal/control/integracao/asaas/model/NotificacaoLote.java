package com.digytal.control.integracao.asaas.model;

import java.util.ArrayList;
import java.util.List;

public class NotificacaoLote {
    private String customer;
    private List<Notificacao> notifications = new ArrayList<>();

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Notificacao> getNotifications() {
        return notifications;
    }

    public String getCustomer() {
        return customer;
    }
}
