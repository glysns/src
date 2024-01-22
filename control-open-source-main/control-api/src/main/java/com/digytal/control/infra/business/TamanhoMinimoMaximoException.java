package com.digytal.control.infra.business;

import static com.digytal.control.infra.business.BusinessMessage.E118;

public class TamanhoMinimoMaximoException extends BusinessException {
    public TamanhoMinimoMaximoException(String campo,Integer minimo, Integer maximo){
        super(E118,campo,minimo.toString(), maximo.toString());
    }
}
