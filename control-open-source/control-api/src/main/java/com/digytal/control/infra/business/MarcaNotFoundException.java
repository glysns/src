package com.digytal.control.infra.business;
import com.digytal.control.infra.commons.validation.Attributes;
import com.digytal.control.infra.commons.validation.Entities;

import javax.print.attribute.Attribute;

import static com.digytal.control.infra.business.BusinessMessage.E134;
import static com.digytal.control.infra.business.BusinessMessage.E404;

public class MarcaNotFoundException extends BusinessException{
    public MarcaNotFoundException(Entities entity, Attributes attrubute) {
        super(E404,entity.getLabel(),attrubute.getLabel());
    }
}
