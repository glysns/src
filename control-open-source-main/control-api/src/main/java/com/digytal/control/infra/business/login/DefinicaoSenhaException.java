package com.digytal.control.infra.business.login;


import com.digytal.control.infra.business.BusinessMessage;
import com.digytal.control.infra.business.BusinessException;

public class DefinicaoSenhaException extends BusinessException {
	public DefinicaoSenhaException(BusinessMessage error) {
		super(error);
	}
	public static DefinicaoSenhaException equal(){
		return new DefinicaoSenhaException(BusinessMessage.E111);
	}
	public static DefinicaoSenhaException notEqual(){
		return new DefinicaoSenhaException(BusinessMessage.E112);
	}
}
