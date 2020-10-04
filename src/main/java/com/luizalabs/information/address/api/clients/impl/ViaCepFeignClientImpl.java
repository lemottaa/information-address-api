package com.luizalabs.information.address.api.clients.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.luizalabs.information.address.api.clients.ViaCepApiFeign;
import com.luizalabs.information.address.api.clients.ViaCepFeignClient;
import com.luizalabs.information.address.api.dto.ViaCepDTO;
import com.luizalabs.information.address.api.exception.InformationAddressFailedException;
import com.luizalabs.information.address.api.exception.SerealizationException;
import com.luizalabs.information.address.api.utils.FeignUtils;

import feign.Response;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ViaCepFeignClientImpl implements ViaCepFeignClient {

	private ViaCepApiFeign viaCepApi;

	@Override
	public ViaCepDTO getViaCepAddress(final String zipCode) throws IOException {

		final Response response = this.getFullAddress(zipCode);
		return getViaCepResponse(zipCode, response);
	}

	@Cacheable
	private Response getFullAddress(final String zipCode) {
		return this.viaCepApi.getFullAddressByZipCode(zipCode);
	}

	private ViaCepDTO getViaCepResponse(final String zipCode, final Response response) throws IOException {
		if (Boolean.FALSE.equals(this.isSucessResponse(response)))
			throw new InformationAddressFailedException("Unexpected error to get information address");
		
		final Optional<ViaCepDTO> optionalBody = FeignUtils.getReturnedBody
				(response.body().asInputStream(), ViaCepDTO.class);
		
		if (optionalBody.isEmpty())
			throw new SerealizationException(String.format("Unexpected error to get data about"
					+ " zipcode - %s", zipCode));

		return optionalBody.get();
	}

	private boolean isSucessResponse(final Response response) {
		return HttpStatus.OK.value() == response.status();
	}

}
