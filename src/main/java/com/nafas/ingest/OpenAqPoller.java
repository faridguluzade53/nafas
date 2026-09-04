package com.nafas.ingest;

import com.nafas.sensor.SensorReading;
import com.nafas.sensor.SensorReadingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class OpenAqPoller {

	private static final Logger log = LoggerFactory.getLogger(OpenAqPoller.class);

	private final OpenAqClient openAqClient;
	private final SensorReadingRepository sensorReadingRepository;
	private final OpenAqProperties properties;

	private boolean warnedMissingKey = false;

	public OpenAqPoller(OpenAqClient openAqClient, SensorReadingRepository sensorReadingRepository, OpenAqProperties properties) {
		this.openAqClient = openAqClient;
		this.sensorReadingRepository = sensorReadingRepository;
		this.properties = properties;
	}

	@Scheduled(fixedDelayString = "${nafas.openaq.poll-interval-ms}")
	public void poll() {
		if (!StringUtils.hasText(properties.getApiKey())) {
			if (!warnedMissingKey) {
				log.warn("OPENAQ_API_KEY not set, skipping OpenAQ polling");
				warnedMissingKey = true;
			}
			return;
		}

		List<SensorReading> readings = openAqClient.fetchLatest();
		int saved = 0;
		for (SensorReading reading : readings) {
			boolean exists = sensorReadingRepository.existsBySensorIdAndRecordedAtAndPollutant(
					reading.getSensorId(), reading.getRecordedAt(), reading.getPollutant());
			if (exists) {
				continue;
			}
			sensorReadingRepository.save(reading);
			saved++;
		}
		log.info("OpenAQ poll fetched {} reading(s), saved {} new", readings.size(), saved);
	}

}
