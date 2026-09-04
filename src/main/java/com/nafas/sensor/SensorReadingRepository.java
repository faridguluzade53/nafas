package com.nafas.sensor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorReadingRepository extends JpaRepository<SensorReading, SensorReadingId> {

	List<SensorReading> findBySensorIdOrderByRecordedAtDesc(String sensorId, Pageable pageable);

	List<SensorReading> findBySensorIdAndPollutantOrderByRecordedAtDesc(
			String sensorId, Pollutant pollutant, Pageable pageable);

}
