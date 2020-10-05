package com.luizalabs.information.address.api.utils;

import java.math.BigDecimal;
import java.util.Date;

import net.logstash.logback.marker.LogstashMarker;
import net.logstash.logback.marker.Markers;

public final class LogUtils {
	
	private LogUtils() {
	}

	public static LogstashMarker buildHttpRequestLogMarker(final String url, final Boolean status,
			final String method, final Date initialTime) {
		
		return buildMarker(url, status, method, initialTime);
	}
	
	private static LogstashMarker buildMarker(final String url, final Boolean status, final String method,
			final Date initialTime) {
		final LogstashMarker httpRequest = Markers.append("http.url", url);
		httpRequest.and(Markers.append("http.status", status));
		httpRequest.and(Markers.append("http.method", method));
		httpRequest.and(Markers.append("http.latency", getLatency(initialTime.getTime())));

		return httpRequest;
	}

	private static Double getLatency(final Long initialTime) {
		BigDecimal latency = new BigDecimal(new Date().getTime() - initialTime);
		latency = latency.divide(new BigDecimal(1000));
		latency = latency.setScale(4);
		
		return latency.doubleValue();
	}

}
