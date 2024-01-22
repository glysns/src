package com.digytal.control.infra.business;

import com.digytal.control.infra.commons.validation.Attributes;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ParametroInvalidoException extends BusinessException{
    public ParametroInvalidoException(Attributes ... parametros){
        super(BusinessMessage.E132,String.format("%s", Arrays.stream(parametros).map(a->a.getLabel()).collect(Collectors.toList())));
    }
    public ParametroInvalidoException(String parametro){
        super(BusinessMessage.E132,parametro);
    }


}

