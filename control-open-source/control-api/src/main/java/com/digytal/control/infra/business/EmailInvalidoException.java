package com.digytal.control.infra.business;

import static com.digytal.control.infra.business.BusinessMessage.E115;

public class EmailInvalidoException extends BusinessException {
    public EmailInvalidoException() {
        super(E115);
    }
}
