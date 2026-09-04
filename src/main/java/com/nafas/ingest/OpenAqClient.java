package com.nafas.ingest;

import com.nafas.sensor.Pollutant;
import com.nafas.sensor.SensorReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OpenAqClient {

	private static final Logger log = LoggerFactory.getLogger(OpenAqClient.class);

	// The /locations/{id}/latest endpoint reports each measurement's sensorsId but not
	// the pollutant it measures, so we resolve sensorsId -> parameter via /locations/{id}
	// (which lists each sensor's parameter) before mapping to our Pollutant enum.
	private static final Map<String, Pollutant> PARAMETER_TO_POLLUTANT = Map.of(
			"pm25", Pollutant.PM25,
			"pm10", Pollutant.PM10,
			"no2", Pollutant.NO2,
			"o3", Pollutant.O3,
			"so2", Pollutant.SO2,
			"co", Pollutant.CO
	);

	private final WebClient webClient;
	private final OpenAqProperties properties;

	public OpenAqClient(WebClient openAqWebClient, OpenAqProperties properties) {
		this.webClient = openAqWebClient;
		this.properties = properties;
	}

	public List<SensorReading> fetchLatest() {
		long locationId = properties.getLocationId();
		Map<Long, Pollutant> sensorPollutants = fetchSensorPollutants(locationId);

		OpenAqLatestResponse latest = webClient.get()
				.uri("/locations/{id}/latest", locationId)
				.retrieve()
				.bodyToMono(OpenAqLatestResponse.class)
				.block();

		if (latest == null || latest.results() == null) {
			return List.of();
		}

		String sensorId = "openaq:" + locationId;
		List<SensorReading> readings = new ArrayList<>();
		for (OpenAqLatestResult result : latest.results()) {
			Pollutant pollutant = sensorPollutants.get(result.sensorsId());
			if (pollutant == null) {
				log.debug("Skipping OpenAQ sensor {} at location {} - no mapped pollutant", result.sensorsId(), locationId);
				continue;
			}
			Instant recordedAt = Instant.parse(result.datetime().utc());
			readings.add(new SensorReading(sensorId, recordedAt, pollutant, result.value()));
		}
		return readings;
	}

	private Map<Long, Pollutant> fetchSensorPollutants(long locationId) {
		OpenAqLocationsResponse response = webClient.get()
				.uri("/locations/{id}", locationId)
				.retrieve()
				.bodyToMono(OpenAqLocationsResponse.class)
				.block();

		if (response == null || response.results() == null || response.results().isEmpty()) {
			return Map.of();
		}

		Map<Long, Pollutant> sensorPollutants = new HashMap<>();
		for (OpenAqSensorInfo sensor : response.results().get(0).sensors()) {
			if (sensor.parameter() == null) {
				continue;
			}
			Pollutant pollutant = PARAMETER_TO_POLLUTANT.get(sensor.parameter().name());
			if (pollutant == null) {
				log.debug("Skipping OpenAQ parameter '{}' at location {} - no mapped Pollutant", sensor.parameter().name(), locationId);
				continue;
			}
			sensorPollutants.put(sensor.id(), pollutant);
		}
		return sensorPollutants;
	}

}
