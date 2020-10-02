package com.luizalabs.information.address.api.controller.advicer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luizalabs.information.address.api.factory.ErrorFactory;
import com.luizalabs.information.address.api.factory.ResponseBodyFactory;

@ControllerAdvice
public class ApiControllerAdvice {

	@ExceptionHandler({MissingServletRequestParameterException.class})
	public ResponseEntity invalidParameterController(final MissingServletRequestParameterException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseBodyFactory.with(ErrorFactory.notPresentParameter(e.getParameterName())));
	}
}
