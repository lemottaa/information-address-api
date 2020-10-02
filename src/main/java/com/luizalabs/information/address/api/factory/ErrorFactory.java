package com.luizalabs.information.address.api.factory;

import com.luizalabs.information.address.api.dto.error.ErrorDTO;
import com.luizalabs.information.address.api.enums.ErrorCodeEnum;

public class ErrorFactory {

	public static ErrorDTO getUnAuthorized() {
		return ErrorDTO.builder()
				.developerMessage("Unauthorized - make sure the header parameter Authorization "
						+ "is valid")
				.userMessage("You are not authorized to perform this operation")
				.errorCode(ErrorCodeEnum.UNAUTHORIZED.getCode()).build();
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
	
	public static ErrorDTO notFound(final String name) {
		return ErrorDTO.builder()
				.developerMessage(String.format(" %s not found", name))
				.userMessage(String.format("You attempted to get a %s, but did not find any", name))
				.errorCode(ErrorCodeEnum.NOT_FOUND.getCode())
				.build();
	}
}
