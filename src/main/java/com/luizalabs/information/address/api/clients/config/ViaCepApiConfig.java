package com.luizalabs.information.address.api.clients.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import feign.RequestInterceptor;

@Profile("!test")
public class ViaCepApiConfig {

	public static final String NAME = "viacep";
	public static final String BASE = "/ws";
	public static final String GET_ADDRESS_BY_CEP = "/{cep}/json";
	
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
        };
    }
}
