package com.digytal.control.infra.business.login;

import com.digytal.control.infra.business.BusinessException;

import static com.digytal.control.infra.business.BusinessMessage.E100;

public class LoginException extends BusinessException {
	public LoginException() {
		super(E100);
	}
}
