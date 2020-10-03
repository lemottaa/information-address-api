package com.luizalabs.information.address.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViaCepDTO {
	
	@JsonProperty("cep")
	private String zipcode;
	
	@JsonProperty("logradouro")
	private String street;
	
	@JsonProperty("complemento")
	private String complement;
	
	@JsonProperty("bairro")
	private String district;
	
	@JsonProperty("localidade")
	private String city;
	
	@JsonProperty("uf")
	private String state;
	
	private String ibge;
	
	private String gia;
	
	private Integer ddd;
	
	private String siafi;
	
	@JsonProperty("erro")
	private Boolean error;
}
