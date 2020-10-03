package com.luizalabs.information.address.api.clients.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.luizalabs.information.address.api.clients.ViaCepApiFeign;
import com.luizalabs.information.address.api.clients.ViaCepFeignClient;
import com.luizalabs.information.address.api.dto.ViaCepDTO;
import com.luizalabs.information.address.api.exception.InformationAddressFailedException;
import com.luizalabs.information.address.api.utils.FeignUtils;

import feign.Response;

@Component
public class ViaCepFeignClientImpl implements ViaCepFeignClient {

	private ViaCepApiFeign viaCepApi;

	@Override
	public ViaCepDTO getViaCepAddress(final String zipCode) throws IOException {

		final Response response = this.viaCepApi.getFullAddressByZipCode(zipCode);

		if (Boolean.FALSE.equals(this.isSucessResponse(response)))
			return null;
		
		final Optional<ViaCepDTO> optionalBody = FeignUtils.getReturnedBody
				(response.body().asInputStream(), ViaCepDTO.class);
		
		if (optionalBody.isEmpty())
			throw new InformationAddressFailedException("Unexpected error to get information address");

		return optionalBody.get();
	}

	private boolean isSucessResponse(final Response response) {
		return HttpStatus.OK.value() == response.status();
	}

}
