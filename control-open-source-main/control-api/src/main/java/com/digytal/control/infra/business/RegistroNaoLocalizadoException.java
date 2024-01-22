package com.digytal.control.infra.business;


import com.digytal.control.infra.commons.validation.Attributes;
import com.digytal.control.infra.commons.validation.Entities;

import static com.digytal.control.infra.business.BusinessMessage.E404;

public class RegistroNaoLocalizadoException extends BusinessException {
    public RegistroNaoLocalizadoException(Entities registro, Attributes campo) {
        super(E404,registro.getLabel(),campo.getLabel());
    }
    public RegistroNaoLocalizadoException(Entities registro) {
        super(E404,registro.getLabel(),"Identificação");
    }
    public RegistroNaoLocalizadoException() {
        super(E404,"Registro", "Código");
    }

}
