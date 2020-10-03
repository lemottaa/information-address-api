package com.luizalabs.information.address.api.clients.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class ViaCepApiConfig {

	public static final String NAME = "viacep";
	public static final String BASE = "/ws";
	public static final String GET_ADDRESS_BY_CEP = "/{cep}/json";
	
	
	@Value("${viacep.api.token}")
    private String viaCepToken;
	
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("Authorization", "Bearer " + viaCepToken);
        };
    }
}
