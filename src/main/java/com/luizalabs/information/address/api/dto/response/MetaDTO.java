package com.luizalabs.information.address.api.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaDTO implements Serializable {

	private static final long serialVersionUID = -8055154398329010302L;
	private String server;
	private Long offset;
	private Integer limit;
	private Integer totalPages;
	private Integer recordCount;
	private Long recordCountTotal;
}
