package com.digytal.control.infra.business;

import static com.digytal.control.infra.business.BusinessMessage.E122;

public class CpfCnpjInvalidoException extends BusinessException{
    public CpfCnpjInvalidoException() {
        super(E122);
    }

}
