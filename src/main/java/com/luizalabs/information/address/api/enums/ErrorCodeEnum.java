package com.luizalabs.information.address.api.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum {

	UNAUTHORIZED(30001L, HttpStatus.UNAUTHORIZED),
	INVALID_PARAMETER(30021L, HttpStatus.BAD_REQUEST);
	
	private Long code;
	
	private HttpStatus httpCode;
	
	private ErrorCodeEnum(final Long code, final HttpStatus httpCode) {
		this.code = code;
		this.httpCode = httpCode;
	}

	public Long getCode() {
		return code;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}
	
	public static HttpStatus findHttpStatus(final Long errorCode) {
		
		for (final ErrorCodeEnum error : ErrorCodeEnum.values()) {
			if (error.getCode().equals(errorCode)) {
				return error.getHttpCode();
			}
		}
		
		return null;
	}
}
