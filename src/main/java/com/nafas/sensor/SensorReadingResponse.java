package com.nafas.sensor;

import java.time.Instant;

public record SensorReadingResponse(
		Long id,
		String sensorId,
		Instant recordedAt,
		Double pm25
) {

	public static SensorReadingResponse from(SensorReading reading) {
		return new SensorReadingResponse(
				reading.getId(),
				reading.getSensorId(),
				reading.getRecordedAt(),
				reading.getPm25()
		);
	}

}
