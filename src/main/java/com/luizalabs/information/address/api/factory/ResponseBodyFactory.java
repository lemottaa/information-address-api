package com.luizalabs.information.address.api.factory;

import com.luizalabs.information.address.api.dto.error.ErrorDTO;
import com.luizalabs.information.address.api.dto.response.MetaDTO;
import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;


public final class ResponseBodyFactory {

	private ResponseBodyFactory() {
		
	}
	
	public static <T> ResponseBodyDTO<T> of(final T t) {
		final ResponseBodyDTO<T> response = new ResponseBodyDTO<>();
		response.setMeta(MetaDTO.builder().build());
		response.getMeta().setRecordCount(1);
		response.addRecord(t);
		return response;
	}

	public static ResponseBodyDTO<ErrorDTO> with(final ErrorDTO error) {
		final ResponseBodyDTO<ErrorDTO> response = new ResponseBodyDTO<>();
		response.addError(error);
		return response;
	}

	public static <T> ResponseBodyDTO<T> with(final T t, final ErrorDTO error) {
		final ResponseBodyDTO<T> response = new ResponseBodyDTO<>();
		response.addRecord(t);
		response.addError(error);
		return response;
	}
}
