package com.luizalabs.information.address.api.controller.advicer;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luizalabs.information.address.api.factory.ErrorFactory;
import com.luizalabs.information.address.api.factory.ResponseBodyFactory;

@ControllerAdvice
public class ApiControllerAdvice {

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity invalidParameterController() {		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseBodyFactory.with(ErrorFactory.invalidParameter("zipcode")));
	}
}
