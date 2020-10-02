package com.luizalabs.information.address.api.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.information.address.api.controller.base.BaseController;
import com.luizalabs.information.address.api.dto.response.AddressResponseDTO;
import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;
import com.luizalabs.information.address.api.service.AddressService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/address")
public class AddressController extends BaseController {

	@Autowired
	private AddressService addressService;
	
	@ApiOperation("Get address by zipcode")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get address was successful"),
			@ApiResponse(code = 404, message = "Address not found") })
	@GetMapping
	public ResponseEntity<ResponseBodyDTO<AddressResponseDTO>> getAddressByZipCode(@Valid 
			@NotEmpty @RequestParam(value = "zipCode", required = true) 
			@Size(min = 0, max = 8) final String zipCode) {
				
		return buildResponse(this.addressService.getAdressByZipCode(zipCode), HttpStatus.OK);
	}
}