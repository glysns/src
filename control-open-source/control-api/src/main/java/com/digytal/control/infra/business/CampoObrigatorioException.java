package com.digytal.control.infra.business;


import com.digytal.control.infra.commons.validation.Attributes;

import static com.digytal.control.infra.business.BusinessMessage.E101;

public class CampoObrigatorioException extends BusinessException{
    public CampoObrigatorioException(Attributes attribute) {
        super(E101,attribute.getLabel());
    }
    public CampoObrigatorioException(String label) {
        super(E101,label);
    }

}
