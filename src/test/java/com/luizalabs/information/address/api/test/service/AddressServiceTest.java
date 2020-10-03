package com.luizalabs.information.address.api.test.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.luizalabs.information.address.api.clients.ViaCepFeignClient;
import com.luizalabs.information.address.api.dto.ViaCepDTO;
import com.luizalabs.information.address.api.dto.response.AddressResponseDTO;
import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;
import com.luizalabs.information.address.api.service.AddressService;
import com.luizalabs.information.address.api.test.annotation.IntegrationTest;

@IntegrationTest
public class AddressServiceTest {
	
	@Autowired
	private AddressService service;
	
	@MockBean
	private ViaCepFeignClient viaCepClient;
	
    @BeforeEach
    void setup() {
    	MockitoAnnotations.initMocks(this);	
    }
    
    @Test
    void shouldReturnResponseOkWithFullAddress() throws IOException {
    	
    	Mockito.when(this.viaCepClient.getViaCepAddress(Mockito.anyString())).thenReturn(buildSuccessViaCepDTO());
    	
    	final ResponseBodyDTO<AddressResponseDTO> response = this.service.getAdressByZipCode("14077080");
    	assertNotNull(response);
    	assertNull(response.getErrors());
    	assertTrue(response.isSucess());
    	assertNotNull(response.getRecords());
    	assertTrue(response.getRecords().size() > 0);
    	assertTrue(response.getRecords().get(0).getStreet().equalsIgnoreCase("Rua Altinópolis"));
    	assertTrue(response.getRecords().get(0).getComplement().equalsIgnoreCase(""));
    	assertTrue(response.getRecords().get(0).getDistrict().equalsIgnoreCase("Quintino Facci I"));
    	assertTrue(response.getRecords().get(0).getCity().equalsIgnoreCase("Ribeirão Preto"));
    	assertTrue(response.getRecords().get(0).getState().equalsIgnoreCase("SP"));
    	assertTrue(response.getRecords().get(0).getZipCode().equalsIgnoreCase("14077-080"));
    }
    
    @Test
    void shouldReturnResponseOkBeforeChangeZipCode() throws IOException {
    	Mockito.when(this.viaCepClient.getViaCepAddress("14588888")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("14588880")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("14588800")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("14588000")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("14580000")).thenReturn(buildChangedViaCepDTO());
    	
    	final ResponseBodyDTO<AddressResponseDTO> response = this.service.getAdressByZipCode("14588888");
    	assertNotNull(response);
    	assertNull(response.getErrors());
    	assertTrue(response.isSucess());
    	assertNotNull(response.getRecords());
    	assertTrue(response.getRecords().size() > 0);
    	assertTrue(response.getRecords().get(0).getCity().equalsIgnoreCase("Guará"));
    	assertTrue(response.getRecords().get(0).getState().equalsIgnoreCase("SP"));
    	assertTrue(response.getRecords().get(0).getZipCode().equalsIgnoreCase("14580-000"));
    }
    
    @Test
    void shouldReturnResponseNotFound() throws IOException {
    	Mockito.when(this.viaCepClient.getViaCepAddress("11111111")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("11111110")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("11111100")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("11111000")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("11110000")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("11100000")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("11000000")).thenReturn(buildNotFoundCepDTO());
    	Mockito.when(this.viaCepClient.getViaCepAddress("10000000")).thenReturn(buildNotFoundCepDTO());
    	
    	final ResponseBodyDTO<AddressResponseDTO> response = this.service.getAdressByZipCode("11111111");
    	assertNotNull(response);
    	assertNotNull(response.getErrors());
    	assertFalse(response.isSucess());
    	assertTrue(response.getErrors().get(0).getDeveloperMessage().equalsIgnoreCase("zipcode 11111111 not found"));
		assertTrue(response.getErrors().get(0).getUserMessage()
				.equalsIgnoreCase("You attempted to get a zipcode 11111111, but did not find any"));
    	assertTrue(response.getErrors().get(0).getErrorCode() == 30023L);
    }

	private ViaCepDTO buildSuccessViaCepDTO() {
		return ViaCepDTO.builder()
				.zipCode("14077-080")
				.street("Rua Altinópolis")
				.complement("")
				.district("Quintino Facci I")
				.city("Ribeirão Preto")
				.state("SP")
				.ibge("3543402")
				.gia("5824")
				.ddd(16)
				.siafi("6969")
				.build();
	}
	
	private ViaCepDTO buildChangedViaCepDTO() {
		return ViaCepDTO.builder()
				.zipCode("14580-000")
				.street("")
				.complement("")
				.district("")
				.city("Guará")
				.state("SP")
				.ibge("3517703")
				.gia("3256")
				.ddd(16)
				.siafi("6455")
				.build();
	}

	private ViaCepDTO buildNotFoundCepDTO() {
		return ViaCepDTO.builder()
				.error(true)
				.build();
	}
}
