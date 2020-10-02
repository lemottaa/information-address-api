package com.luizalabs.information.address.api.factory;

import java.util.List;

import com.luizalabs.information.address.api.dto.error.ErrorDTO;
import com.luizalabs.information.address.api.dto.response.MetaDTO;
import com.luizalabs.information.address.api.dto.response.ResponseBodyDTO;


public class ResponseBodyFactory {
	
	public static <T, R> ResponseBodyDTO<?> of(final List<R> list, final Long offSet, final Integer size,
			final Integer totalPages,
			final Long totalElements, final Integer numberOfElements) {
		final ResponseBodyDTO<R> response = new ResponseBodyDTO<>();
		addMetaOnResponse(response, offSet, size, totalPages, totalElements, numberOfElements);
		response.setRecords(list);
		return response;
	}

	public static <T> ResponseBodyDTO<T> of(final List<T> list) {
		final ResponseBodyDTO<T> response = new ResponseBodyDTO<>();
		response.getMeta().setRecordCount(list.size());
		list.forEach(response::addRecord);
		return response;
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

	public static ResponseBodyDTO<ErrorDTO> with(final List<ErrorDTO> errors) {
		final ResponseBodyDTO<ErrorDTO> response = new ResponseBodyDTO<>();
		response.setErrors(errors);
		return response;
	}

	public static <T> ResponseBodyDTO<T> with(final T t, final ErrorDTO error) {
		final ResponseBodyDTO<T> response = new ResponseBodyDTO<>();
		response.addRecord(t);
		response.addError(error);
		return response;
	}

	public static <T> ResponseBodyDTO<T> with(final List<T> list, final ErrorDTO error) {
		final ResponseBodyDTO<T> response = new ResponseBodyDTO<>();
		response.setRecords(list);
		response.addError(error);
		return response;
	}

	private static void addMetaOnResponse(final ResponseBodyDTO<?> response, final Long offSet, final Integer size,
			final Integer totalPages, final Long totalElements, final Integer numberOfElements) {
		response.setMeta(response.getMeta());
		response.getMeta().setOffset(offSet);
		response.getMeta().setLimit(size);
		response.getMeta().setTotalPages(totalPages);
		response.getMeta().setRecordCountTotal(totalElements);
		response.getMeta().setRecordCount(numberOfElements);
	}
}
