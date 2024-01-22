package com.digytal.control.infra.business;


import static com.digytal.control.infra.business.BusinessMessage.E123;
public class UsuarioInvalidoException extends BusinessException{

    public UsuarioInvalidoException() {
        super(E123);
    }

}
