package com.digytal.control.infra.business;

public class IntegracaoException extends BusinessException{
    public IntegracaoException(String mensagem){
        super(BusinessMessage.E131,mensagem);
    }

}

