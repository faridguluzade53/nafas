package com.nafas.sensor;

import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SensorReadingController {

	private static final int DEFAULT_LIMIT = 20;
	private static final int MAX_LIMIT = 100;

	private final SensorReadingRepository sensorReadingRepository;

	public SensorReadingController(SensorReadingRepository sensorReadingRepository) {
		this.sensorReadingRepository = sensorReadingRepository;
	}

	@PostMapping("/api/sensor-readings")
	public ResponseEntity<SensorReadingResponse> create(@Valid @RequestBody CreateSensorReadingRequest request) {
		SensorReading reading = new SensorReading(request.sensorId(), request.recordedAt(), request.pollutant(), request.value());
		SensorReading saved = sensorReadingRepository.save(reading);
		return ResponseEntity.status(HttpStatus.CREATED).body(SensorReadingResponse.from(saved));
	}

	@GetMapping("/api/sensor-readings")
	public ResponseEntity<List<SensorReadingResponse>> findBySensorId(
			@RequestParam String sensorId,
			@RequestParam(required = false, defaultValue = "" + DEFAULT_LIMIT) int limit) {
		int clampedLimit = Math.max(1, Math.min(limit, MAX_LIMIT));
		List<SensorReadingResponse> readings = sensorReadingRepository
				.findBySensorIdOrderByRecordedAtDesc(sensorId, PageRequest.of(0, clampedLimit, Sort.by("recordedAt").descending()))
				.stream()
				.map(SensorReadingResponse::from)
				.toList();
		return ResponseEntity.ok(readings);
	}

}
