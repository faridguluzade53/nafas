package com.nafas.sensor;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorReadingController {

	private final SensorReadingRepository sensorReadingRepository;

	public SensorReadingController(SensorReadingRepository sensorReadingRepository) {
		this.sensorReadingRepository = sensorReadingRepository;
	}

	@PostMapping("/api/sensor-readings")
	public ResponseEntity<SensorReadingResponse> create(@Valid @RequestBody CreateSensorReadingRequest request) {
		SensorReading reading = new SensorReading(request.sensorId(), request.recordedAt(), request.pm25());
		SensorReading saved = sensorReadingRepository.save(reading);
		return ResponseEntity.status(HttpStatus.CREATED).body(SensorReadingResponse.from(saved));
	}

}
