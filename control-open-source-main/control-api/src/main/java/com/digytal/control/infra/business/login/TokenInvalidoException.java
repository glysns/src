package com.digytal.control.infra.business.login;

import com.digytal.control.infra.business.BusinessException;

import static com.digytal.control.infra.business.BusinessMessage.E900;
public class TokenInvalidoException extends BusinessException {
    public TokenInvalidoException() {
        super(E900);
    }
}