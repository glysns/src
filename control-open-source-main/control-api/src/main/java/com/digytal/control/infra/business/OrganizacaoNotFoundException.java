package com.digytal.control.infra.business;
import com.digytal.control.infra.commons.validation.Attributes;
import com.digytal.control.infra.commons.validation.Entities;

import static com.digytal.control.infra.business.BusinessMessage.E133;
import static com.digytal.control.infra.business.BusinessMessage.E404;

public class OrganizacaoNotFoundException extends BusinessException{
    public OrganizacaoNotFoundException(Entities registro, Attributes attribute) {
        super(E404,registro.getLabel(),attribute.getLabel());
    }
}
