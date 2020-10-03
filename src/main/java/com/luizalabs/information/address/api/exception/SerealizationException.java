package com.luizalabs.information.address.api.exception;

import java.io.IOException;

public class SerealizationException extends IOException {

	private static final long serialVersionUID = 5488452106219936186L;

	public SerealizationException(final String message) {
		super(message);
	}
}
