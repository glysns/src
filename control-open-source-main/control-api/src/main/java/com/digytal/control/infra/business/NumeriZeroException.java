package com.digytal.control.infra.business;

import com.digytal.control.infra.commons.validation.Attributes;

import static com.digytal.control.infra.business.BusinessMessage.E135;

public class NumeriZeroException extends BusinessException{
    public NumeriZeroException(Attributes attribute){
        super(E135,attribute.getLabel());
    }
    public NumeriZeroException(String label){
        super(E135,label);
    }
}
