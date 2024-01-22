package com.digytal.control.infra.business.login;


import com.digytal.control.infra.business.BusinessException;

import static com.digytal.control.infra.business.BusinessMessage.E108;

public class SenhaExpiradaException extends BusinessException {
	public SenhaExpiradaException() {
		super(E108);
	}
}
