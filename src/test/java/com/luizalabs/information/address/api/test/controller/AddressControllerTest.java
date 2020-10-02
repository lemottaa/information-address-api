package com.luizalabs.information.address.api.test.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.luizalabs.information.address.api.service.AddressService;
import com.luizalabs.information.address.api.test.annotation.IntegrationTest;

@IntegrationTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {

	private static final String GET_ADDRESS_BY_ZIPCODE = "/api/address";
	
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressControllerTest controller;

    @MockBean
    private AddressService loadAnticipateService;
    
    @BeforeAll
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }
    
}
