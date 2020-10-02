package com.luizalabs.information.address.api.service;

import com.luizalabs.information.address.api.dto.response.AddressResponseDTO;
import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;

public interface AddressService {
	
	ResponseBodyDTO<AddressResponseDTO> getAdressByZipCode(String zipCode);

}
