package com.digytal.control.infra.business.login;

import com.digytal.control.infra.business.BusinessException;

import static com.digytal.control.infra.business.BusinessMessage.E109;

public class UsuarioBloqueadoException extends BusinessException {
	public UsuarioBloqueadoException() {
		super(E109);
	}
}
