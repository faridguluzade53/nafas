package com.nafas.sensor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
class SensorReadingRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(DockerImageName.parse("timescale/timescaledb:latest-pg17")
                    .asCompatibleSubstituteFor("postgres"));

    @DynamicPropertySource
    static void configureProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    SensorReadingRepository repository;

    @Test
    void savesAndReadsBackByCompositeKey() {
        SensorReading reading = new SensorReading("sensor-1", Instant.now(), Pollutant.PM25, 42.5);

        SensorReading saved = repository.save(reading);

        // id is only known after save (IDENTITY generation) — build the key from the saved entity
        SensorReadingId id = new SensorReadingId(saved.getId(), saved.getRecordedAt());

        Optional<SensorReading> found = repository.findById(id);

        assertThat(found).isPresent();
        assertThat(found.get().getSensorId()).isEqualTo("sensor-1");
        assertThat(found.get().getPollutant()).isEqualTo(Pollutant.PM25);
        assertThat(found.get().getValue()).isEqualTo(42.5);
    }
}