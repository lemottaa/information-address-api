package com.luizalabs.information.address.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.luizalabs.information.address.api.clients.config.ViaCepApiConfig;

import feign.Response;

@FeignClient(name = ViaCepApiConfig.NAME, url = "${viacep.api.url}", path = ViaCepApiConfig.BASE)
public interface ViaCepApiFeign {

	@GetMapping(ViaCepApiConfig.GET_ADDRESS_BY_CEP)
	Response getFullAddressByZipCode(@PathVariable("cep") String zipCode);
	
}
