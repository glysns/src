package com.digytal.control.infra.business;



import static com.digytal.control.infra.business.BusinessMessage.E117;

public class TamanhoMaximoException extends BusinessException{
    public TamanhoMaximoException(String campo,Integer maximo){
        super(E117,campo,maximo.toString());
    }
}
