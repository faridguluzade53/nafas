package com.nafas.sensor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorReadingRepository extends JpaRepository<SensorReading, SensorReadingId> {
}
