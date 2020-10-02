package com.luizalabs.information.address.api.validator.impl;

import org.springframework.stereotype.Component;

import com.luizalabs.information.address.api.validator.ZipCodeNumberValidator;

@Component
public class ZipCodeNumberValidatorImpl implements ZipCodeNumberValidator {

	@Override
	public Boolean isValid(final String value) {
		return (value.matches("[0-9]+") && value.length() == 8);
	}

}
