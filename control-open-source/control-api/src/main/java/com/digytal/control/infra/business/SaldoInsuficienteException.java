package com.digytal.control.infra.business;

import static com.digytal.control.infra.business.BusinessMessage.E126;

public class SaldoInsuficienteException extends BusinessException{
    public SaldoInsuficienteException(){
        super(E126);
    }
}
