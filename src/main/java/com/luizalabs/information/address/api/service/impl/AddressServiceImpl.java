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
import com.luizalabs.information.address.api.utils.ConverterUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private ViaCepFeignClient viaCepClient;
	
	@Override
	public ResponseBodyDTO<AddressResponseDTO> getAdressByZipCode(String zipCode) throws IOException {	
		log.info("Let's start the process by zipcode - {}", zipCode);
		
		final ResponseBodyDTO<AddressResponseDTO> response = new ResponseBodyDTO<>();
		final String zipCodeOrigin = zipCode;
		int index = zipCode.length();		
		
		do {

			final ViaCepDTO responseClient = this.viaCepClient.getViaCepAddress(zipCode);
			if (responseClient.getError() == null) {
				log.info("ViaCep responsed with success by zipode {} - {}", zipCode,
						ConverterUtils.convertObjectToJson(responseClient));
				return ResponseBodyFactory.of(this.buildSuccesAddresResponseDTO(responseClient));
			}
			
			zipCode = this.findSuccessZipCode(zipCode, index - 1);		
			log.info("The zipCode {} not found. Let's try with new zipcode {}", zipCodeOrigin, zipCode);
			index--;
			
		} while (index > 0);
		
		log.info("The zipcode {} was not found and no other similar", zipCodeOrigin);
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
