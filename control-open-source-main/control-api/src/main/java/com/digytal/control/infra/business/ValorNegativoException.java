package com.digytal.control.infra.business;

import com.digytal.control.infra.commons.validation.Attributes;

import static com.digytal.control.infra.business.BusinessMessage.E125;

public class ValorNegativoException extends BusinessException{
    public ValorNegativoException(Attributes attribute){
        super(E125,attribute.getLabel());
    }
}
