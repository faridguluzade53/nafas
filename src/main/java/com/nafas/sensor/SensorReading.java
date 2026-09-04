package com.nafas.sensor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.time.Instant;

// @IdClass over @EmbeddedId: id and recordedAt are each meaningful standalone columns
// (not a value object) — the composite key only exists because TimescaleDB hypertables
// require the partitioning column in the primary key.
@Entity
@Table(name = "sensor_reading")
@IdClass(SensorReadingId.class)
public class SensorReading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Id
	@Column(name = "recorded_at", nullable = false)
	private Instant recordedAt;

	@Column(name = "sensor_id", nullable = false, length = 64)
	private String sensorId;

	@Enumerated(EnumType.STRING)
	@Column(name = "pollutant", nullable = false, length = 10)
	private Pollutant pollutant;

	@Column(name = "value")
	private Double value;

	protected SensorReading() {
	}

	public SensorReading(String sensorId, Instant recordedAt, Pollutant pollutant, Double value) {
		this.sensorId = sensorId;
		this.recordedAt = recordedAt;
		this.pollutant = pollutant;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public Instant getRecordedAt() {
		return recordedAt;
	}

	public String getSensorId() {
		return sensorId;
	}

	public Pollutant getPollutant() {
		return pollutant;
	}

	public Double getValue() {
		return value;
	}

}
