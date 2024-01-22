package com.digytal.control.infra.business;

public class RegistroIncompativelException extends BusinessException{
    public RegistroIncompativelException(String campo){
        super(BusinessMessage.E127,campo);
    }

}

