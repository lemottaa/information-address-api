package com.luizalabs.information.address.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.information.address.api.dto.response.AddressResponseDTO;
import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;
import com.luizalabs.information.address.api.factory.ErrorFactory;
import com.luizalabs.information.address.api.service.AddressService;
import com.luizalabs.information.address.api.validator.ZipCodeNumberValidator;

@Service
public class AddressServiceImpl implements AddressService {


	@Autowired
	private ZipCodeNumberValidator zipCodeValidator;
	
	@Override
	public ResponseBodyDTO<AddressResponseDTO> getAdressByZipCode(final String zipCode) {
		
		final ResponseBodyDTO<AddressResponseDTO> response = new ResponseBodyDTO<>(); 
		
		if (Boolean.FALSE.equals(this.zipCodeValidator.isValid(zipCode))) {
			response.addError(ErrorFactory.invalidParameter("zipCode"));
			return response;
		}
		
		return null;
	}

}
