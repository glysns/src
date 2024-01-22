package com.digytal.control.infra.business;

public class ConfiguracaoContaEmpresaInvalidaException extends BusinessException{
    public ConfiguracaoContaEmpresaInvalidaException(String formaPagamento){
        super(BusinessMessage.E130, formaPagamento);
    }

}

