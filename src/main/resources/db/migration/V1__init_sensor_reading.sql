CREATE EXTENSION IF NOT EXISTS timescaledb;

CREATE TABLE sensor_reading (
    id BIGSERIAL,
    sensor_id VARCHAR(64) NOT NULL,
    recorded_at TIMESTAMPTZ NOT NULL,
    pm25 DOUBLE PRECISION,
    PRIMARY KEY (id, recorded_at)
);

SELECT create_hypertable('sensor_reading', 'recorded_at');
