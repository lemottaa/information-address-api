package com.luizalabs.information.address.api.utils;

import com.google.gson.Gson;
public final class ConverterUtils {
	
	private ConverterUtils() {
		
	}

	public static String convertObjectToJson(final Object object) {
		final Gson gson = new Gson();
		return gson.toJson(object);
	}
}
