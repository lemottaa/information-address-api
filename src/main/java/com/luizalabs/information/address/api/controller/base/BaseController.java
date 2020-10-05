package com.luizalabs.information.address.api.controller.base;

import java.util.Date;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;
import com.luizalabs.information.address.api.enums.ErrorCodeEnum;
import com.luizalabs.information.address.api.utils.LogUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseController {

	protected <T> ResponseEntity<ResponseBodyDTO<T>> buildResponse(final ResponseBodyDTO<T> responseBody,
			final HttpStatus httpStatusSuccess, final String url, final HttpMethod method, 
			final Date initialTime) {
		if (responseBody.isSucess()) {
			log.info(String.format("http_request - HtpStatus:%s", HttpStatus.OK),
					LogUtils.buildHttpRequestLogMarker(url, true, method.name(), initialTime));
			return ResponseEntity.status(httpStatusSuccess).body(responseBody);
		}
		
		final HttpStatus errorStatus = ErrorCodeEnum.findHttpStatus(responseBody.getErrors().get(0)
				.getErrorCode());
		
		log.error(String.format("http_request - HtpStatus:%s", errorStatus),
				LogUtils.buildHttpRequestLogMarker(url, false, method.name(), initialTime));
		
		return errorResponse(responseBody, errorStatus);
	}
	
	protected <T> ResponseEntity<ResponseBodyDTO<T>> errorResponse(final ResponseBodyDTO<T> responseBody,
			final HttpStatus errorStatus) {
		return ResponseEntity.status(errorStatus).body(responseBody);
	}
}
