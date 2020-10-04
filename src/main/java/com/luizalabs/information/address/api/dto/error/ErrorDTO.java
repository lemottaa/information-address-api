package com.luizalabs.information.address.api.dto.error;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements Serializable {
	
	private static final long serialVersionUID = 2769744592527433278L;
	private String developerMessage;
	private String userMessage;
	private String moreInfo;
	private long errorCode;
}
