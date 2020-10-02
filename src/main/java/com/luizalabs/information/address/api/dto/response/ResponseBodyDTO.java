package com.luizalabs.information.address.api.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luizalabs.information.address.api.dto.error.ErrorDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseBodyDTO<T> implements Serializable {

	private static final long serialVersionUID = -3407534158291034949L;

	private MetaDTO meta;
	private List<ErrorDTO> errors;
	private List<T> records;
	
	public void addRecord(final T record) {
		if (this.records == null) {
			records = new ArrayList<>();
		}

		records.add(record);
		
	}
	public void addError(final ErrorDTO error) {
		if (errors == null) {
			errors = new ArrayList<>();
		}

		errors.add(error);
		
	}
	public boolean isSucess() {
		if ((errors == null) || errors.isEmpty()) {
			return true;
		}

		return false;
	}
}
