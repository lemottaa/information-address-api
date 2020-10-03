package com.luizalabs.information.address.api.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class FeignUtils {
	
    private FeignUtils() {
    }
    
	public static <T> Optional<T> getReturnedBody(final InputStream body, final Class<T> klass) {
	    try {
	        final String bodyJson = new BufferedReader(new InputStreamReader(body))
	                .lines().parallel().collect(Collectors.joining("\n"));
	        return Optional.ofNullable(new ObjectMapper().readValue(bodyJson, klass));
	    } catch (final IOException e) {
	        return Optional.empty();
	    }
	}

}
