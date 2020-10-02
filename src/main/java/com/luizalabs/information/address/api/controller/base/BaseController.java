package com.luizalabs.information.address.api.controller.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;
import com.luizalabs.information.address.api.enums.ErrorCodeEnum;


public abstract class BaseController {

	protected <T> ResponseEntity<ResponseBodyDTO<T>> buildResponse(final ResponseBodyDTO<T> responseBody,
			final HttpStatus httpStatusSuccess) {
		return responseBody.isSucess() ?
				ResponseEntity.status(httpStatusSuccess).body(responseBody) :
					errorResponse(responseBody);
	}
	
	protected <T> ResponseEntity<ResponseBodyDTO<T>> errorResponse(final ResponseBodyDTO<T> responseBody) {
		return ResponseEntity.status(ErrorCodeEnum.findHttpStatus(responseBody.getErrors().get(0)
				.getErrorCode())).body(responseBody);
	}
}
