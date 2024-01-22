package com.digytal.control.infra.business;

public class CampoRestritoException extends BusinessException{
    public CampoRestritoException(String campo){
        super(BusinessMessage.E128,campo);
    }

}

