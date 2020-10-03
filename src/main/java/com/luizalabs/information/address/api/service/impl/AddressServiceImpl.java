package com.luizalabs.information.address.api.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.information.address.api.clients.ViaCepFeignClient;
import com.luizalabs.information.address.api.dto.ViaCepDTO;
import com.luizalabs.information.address.api.dto.response.AddressResponseDTO;
import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;
import com.luizalabs.information.address.api.factory.ErrorFactory;
import com.luizalabs.information.address.api.factory.ResponseBodyFactory;
import com.luizalabs.information.address.api.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private ViaCepFeignClient viaCepClient;
	
	@Override
	public ResponseBodyDTO<AddressResponseDTO> getAdressByZipCode(String zipCode) throws IOException {		
		final ResponseBodyDTO<AddressResponseDTO> response = new ResponseBodyDTO<>();
		final String zipCodeOrigin = zipCode;
		int index = zipCode.length();		
		
		do {

			final ViaCepDTO responseClient = this.viaCepClient.getViaCepAddress(zipCode);
			if (responseClient.getError() == null)
				return ResponseBodyFactory.of(this.buildSuccesAddresResponseDTO(responseClient));
			
			zipCode = this.findSuccessZipCode(zipCode, index - 1);			
			index--;
			
		} while (index > 0);
		
		response.addError(ErrorFactory.notFound("zipcode", zipCodeOrigin));
		return response;		
	}

	private String findSuccessZipCode(final String zipCode, final int index) {
		final String[] zipCodeChars = zipCode.split("");
		zipCodeChars[index] = "0";
		return String.join("", zipCodeChars);
	}

	private AddressResponseDTO buildSuccesAddresResponseDTO(final ViaCepDTO responseClient) {
		return AddressResponseDTO.builder()
				.zipCode(responseClient.getZipCode())
				.street(responseClient.getStreet())
				.complement(responseClient.getComplement())
				.district(responseClient.getDistrict())
				.city(responseClient.getCity())
				.state(responseClient.getState())
				.build();
	}

}
