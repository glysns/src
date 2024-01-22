package com.digytal.control.infra.business;

import static com.digytal.control.infra.business.BusinessMessage.E116;

public class TamanhoMinimoException extends BusinessException{
    public TamanhoMinimoException(String campo, Integer minimo){
        super(E116,campo,minimo.toString());
    }
}
