package com.digytal.control.infra.business;

import static com.digytal.control.infra.business.BusinessMessage.E500;

public class ErroNaoMapeadoException extends BusinessException{
    public ErroNaoMapeadoException(){
        super(E500);
    }
}
