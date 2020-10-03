package com.luizalabs.information.address.api.test.client;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.luizalabs.information.address.api.clients.ViaCepFeignClient;
import com.luizalabs.information.address.api.dto.ViaCepDTO;
import com.luizalabs.information.address.api.exception.InformationAddressFailedException;
import com.luizalabs.information.address.api.exception.SerealizationException;
import com.luizalabs.information.address.api.test.annotation.IntegrationClientTest;

@IntegrationClientTest
public class ViaCepApiClientTest {
	
	@Autowired
	private ViaCepFeignClient viaCepClient;
	
	@Test
	void shouldReturnInformationAddressFailedExceptionOnGetFullAddress() {		
		Assertions.assertThrows(InformationAddressFailedException.class,
				() -> this.viaCepClient.getViaCepAddress("345567"));
	}
	
	@Test
	void shouldReturnSerealizationExceptionOnGetFullAddress() {		
		Assertions.assertThrows(SerealizationException.class,
				() -> this.viaCepClient.getViaCepAddress("14580000"));
	}
	
	@Test
	void shouldReturnViaCepDTOWithError() throws IOException {		
		final ViaCepDTO response = this.viaCepClient.getViaCepAddress("22222221");
		
		assertTrue(response.getError());
	}
	
	@Test
	void shouldReturnSuccessViaCepDTO() throws IOException {		
		final ViaCepDTO response = this.viaCepClient.getViaCepAddress("14077080");
		
		assertTrue(response.getZipCode().equalsIgnoreCase("14077-080"));
		assertTrue(response.getStreet().equalsIgnoreCase("Rua Altinópolis"));
		assertTrue(response.getComplement().equalsIgnoreCase(""));
		assertTrue(response.getDistrict().equalsIgnoreCase("Quintino Facci I"));
		assertTrue(response.getCity().equalsIgnoreCase("Ribeirão Preto"));
		assertTrue(response.getState().equalsIgnoreCase("SP"));
		assertTrue(response.getIbge().equalsIgnoreCase("3543402"));
		assertTrue(response.getGia().equalsIgnoreCase("5824"));
		assertTrue(response.getDdd().equals(16));
		assertTrue(response.getSiafi().equalsIgnoreCase("6969"));
	}
}
