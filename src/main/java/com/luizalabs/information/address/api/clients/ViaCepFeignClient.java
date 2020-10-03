package com.luizalabs.information.address.api.clients;

import java.io.IOException;

import com.luizalabs.information.address.api.dto.ViaCepDTO;

public interface ViaCepFeignClient {
	ViaCepDTO getViaCepAddress(String zipCode) throws IOException;
}
