package com.luizalabs.information.address.api.service.impl;

import org.springframework.stereotype.Service;

import com.luizalabs.information.address.api.dto.response.AddressResponseDTO;
import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;
import com.luizalabs.information.address.api.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Override
	public ResponseBodyDTO<AddressResponseDTO> getAdressByZipCode(final String zipCode) {
		
		final ResponseBodyDTO<AddressResponseDTO> response = new ResponseBodyDTO<>(); 
		
		
		
		return response;
	}

}
