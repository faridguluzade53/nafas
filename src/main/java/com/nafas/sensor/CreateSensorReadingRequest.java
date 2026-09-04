package com.nafas.sensor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.Instant;

public record CreateSensorReadingRequest(
		@NotBlank String sensorId,
		@NotNull Instant recordedAt,
		@NotNull Pollutant pollutant,
		@NotNull @PositiveOrZero Double value
) {
}
