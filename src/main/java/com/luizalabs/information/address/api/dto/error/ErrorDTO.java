package com.luizalabs.information.address.api.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
	
	private String developerMessage;
	private String userMessage;
	private String moreInfo;
	private long errorCode;
}
