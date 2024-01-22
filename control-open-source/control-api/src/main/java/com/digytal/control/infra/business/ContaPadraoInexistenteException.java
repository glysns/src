package com.digytal.control.infra.business;

import static com.digytal.control.infra.business.BusinessMessage.E129;

public class ContaPadraoInexistenteException extends BusinessException{
    public ContaPadraoInexistenteException(){
        super(E129);
    }
}
