package com.nafas.sensor;

import java.time.Instant;

public record SensorReadingResponse(
		Long id,
		String sensorId,
		Instant recordedAt,
		Pollutant pollutant,
		Double value
) {

	public static SensorReadingResponse from(SensorReading reading) {
		return new SensorReadingResponse(
				reading.getId(),
				reading.getSensorId(),
				reading.getRecordedAt(),
				reading.getPollutant(),
				reading.getValue()
		);
	}

}
