package com.luizalabs.information.address.api.security;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Value;

@Generated
public class JwtConfig {
	
	@Value("${security.jwt.uri:/**}")
	private String uri;

	@Value("${security.jwt.header:Authorization}")
	private String header;

	@Value("${security.jwt.prefix:Bearer}")
	private String prefix;

	@Value("${security.jwt.secret}")
	private String secret;

	public String getUri() {
		return uri;
	}

	public String getHeader() {
		return header;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSecret() {
		return secret;
	}
}

