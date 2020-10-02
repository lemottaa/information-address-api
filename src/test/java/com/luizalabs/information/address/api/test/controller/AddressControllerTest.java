package com.luizalabs.information.address.api.test.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.luizalabs.information.address.api.controller.AddressController;
import com.luizalabs.information.address.api.controller.advicer.ApiControllerAdvice;
import com.luizalabs.information.address.api.dto.response.AddressResponseDTO;
import com.luizalabs.information.address.api.factory.ErrorFactory;
import com.luizalabs.information.address.api.factory.ResponseBodyFactory;
import com.luizalabs.information.address.api.service.AddressService;
import com.luizalabs.information.address.api.test.annotation.IntegrationTest;

@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {

	private static final String GET_ADDRESS_BY_ZIPCODE = "/api/address";
	
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressController controller;

    @MockBean
    private AddressService addressService;
    
    @BeforeAll
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(ApiControllerAdvice.class)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }
    
    @Test
    public void shouldReturnBadRequestBecauseZipCodeIsntInformed() throws Exception {
    	mockMvc.perform(get(GET_ADDRESS_BY_ZIPCODE)
    			.contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().is4xxClientError())
    	.andExpect(content().string(containsString("The parameter zipcode was not informed")))
				.andExpect(content().string(containsString(
						"There is something wrong - The parameter zipcode was not informed")))
    	.andExpect(content().string(containsString("30022")));
    }
    
    @Test
    public void shouldReturnBadRequestBecauseZipCodeIsntValid() throws Exception {
    	mockMvc.perform(get(GET_ADDRESS_BY_ZIPCODE.concat("?zipcode=12345"))
    			.contentType(MediaType.APPLICATION_JSON))
    	.andExpect(status().is4xxClientError())
    	.andExpect(content().string(containsString("The zipcode parameter is invalid")))
				.andExpect(content().string(containsString("There is something wrong - "
						+ "The zipcode is invalid. Change it and try again")))
    	.andExpect(content().string(containsString("30021")));
    }
    
    @Test
    public void shouldReturnNotFound() throws Exception {
		Mockito.when(this.addressService.getAdressByZipCode(Mockito.anyString()))
				.thenReturn(ResponseBodyFactory.with(AddressResponseDTO.builder().build(),
						ErrorFactory.notFound("zipcode")));
    	
    	mockMvc.perform(get(GET_ADDRESS_BY_ZIPCODE.concat("?zipcode=00000001"))
    			.contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().is4xxClientError())
    	.andExpect(content().string(containsString("zipcode not found")))
				.andExpect(content().string(containsString("You attempted to get a zipcode,"
						+ " but did not find any")))
    	.andExpect(content().string(containsString("30023")));
    }
    
    @Test
    public void shouldReturnOk() throws Exception {
		Mockito.when(this.addressService.getAdressByZipCode(Mockito.anyString()))
				.thenReturn(ResponseBodyFactory.of(AddressResponseDTO.builder().build()));
    	
    	mockMvc.perform(get(GET_ADDRESS_BY_ZIPCODE.concat("?zipcode=14077080"))
    			.contentType(MediaType.APPLICATION_JSON))
    	.andDo(print())
    	.andExpect(status().is2xxSuccessful());
    }
    
}
