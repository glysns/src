package com.digytal.control.infra.business;

import static com.digytal.control.infra.business.BusinessMessage.E204;

public class ConsultaSemRegistrosException extends BusinessException {
    public ConsultaSemRegistrosException() {
        super(E204);
    }
}
