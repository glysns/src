package com.digytal.control.infra.business;

import com.digytal.control.infra.commons.validation.Attributes;

import static com.digytal.control.infra.business.BusinessMessage.E102;

public class RegistroDuplicadoException extends BusinessException {
    public RegistroDuplicadoException(Attributes attributes, String valor) {
        super(E102,attributes.getLabel(),valor);
    }
}
