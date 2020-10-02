package com.luizalabs.information.address.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaDTO {

	private String server;
	private Long offset;
	private Integer limit;
	private Integer totalPages;
	private Integer recordCount;
	private Long recordCountTotal;
}
