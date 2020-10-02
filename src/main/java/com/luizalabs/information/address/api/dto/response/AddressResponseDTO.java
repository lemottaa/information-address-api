package com.luizalabs.information.address.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {
	
	private String zipCode;
	
	private String street;
	
	private String complement;
	
	private String district;
	
	private String city;
	
	private String state;
}
