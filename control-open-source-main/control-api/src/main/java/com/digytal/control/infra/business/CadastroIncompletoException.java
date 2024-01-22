package com.digytal.control.infra.business;


import static com.digytal.control.infra.business.BusinessMessage.E124;

public class CadastroIncompletoException extends BusinessException {
    public CadastroIncompletoException(String mensagem) {
        super(E124,mensagem);
    }


}
