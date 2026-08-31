package com.nafas.sensor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class SensorReadingId implements Serializable {

	private Long id;
	private Instant recordedAt;

	public SensorReadingId() {
	}

	public SensorReadingId(Long id, Instant recordedAt) {
		this.id = id;
		this.recordedAt = recordedAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SensorReadingId that)) {
			return false;
		}
		return Objects.equals(id, that.id) && Objects.equals(recordedAt, that.recordedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, recordedAt);
	}

}
