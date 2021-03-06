package com.luizalabs.information.address.api.factory;

import com.luizalabs.information.address.api.dto.error.ErrorDTO;
import com.luizalabs.information.address.api.enums.ErrorCodeEnum;

public final class ErrorFactory {
	
	private ErrorFactory() {
		
	}
	
	public static ErrorDTO invalidParameter(final String name) {
		return ErrorDTO.builder()
				.developerMessage(String.format("The %s parameter is invalid", name))
				.userMessage(String.format("There is something wrong - The %s is invalid. "
						+ "Change it and try again", name))
				.errorCode(ErrorCodeEnum.INVALID_PARAMETER.getCode())
				.build();
	}
	
	public static ErrorDTO notPresentParameter(final String name) {
		return ErrorDTO.builder()
				.developerMessage(String.format("The parameter %s was not informed", name))
				.userMessage(String.format("There is something wrong - The parameter %s "
						+ "was not informed", name))
				.errorCode(ErrorCodeEnum.NOT_PRESENT_PARAMETER.getCode())
				.build();
	}
	
	public static ErrorDTO notFound(final String name, final String value) {
		return ErrorDTO.builder()
				.developerMessage(String.format("%s %s not found", name, value))
				.userMessage(String.format("You attempted to get a %s %s, but did not find any", name,
						value))
				.errorCode(ErrorCodeEnum.NOT_FOUND.getCode())
				.build();
	}
}
