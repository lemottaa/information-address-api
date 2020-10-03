package com.luizalabs.information.address.api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class ConverterUtils {
	
	private ConverterUtils() {
		
	}

	public static <T> T convertStringToClass(final String message, final Class<T> targetClass) {
		final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		return gson.fromJson(message, targetClass);
	}
}
