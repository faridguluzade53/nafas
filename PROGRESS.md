# Nafas — Progress

Build checklist for the platform itself. No phases, no gates — just what's actually 
built and verified, updated after each session.

## Foundation
- [x] Spring Boot app scaffold (Java 21, Spring Boot 4.1.1, Maven)
- [x] Health check endpoint (GET /api/health)
- [x] PostgreSQL/TimescaleDB running locally via Docker Compose
- [x] First hypertable (sensor_reading) + JPA entity/repository, verified via Testcontainers
- [ ] Full domain model: Reading, Station, Pollutant, GeoPoint, AqiBand
- [ ] Full schema: stations, readings, users, devices, alert_rules, alert_events, location_pings

## 1. Ingest
- [ ] REST endpoint for community devices to POST readings
- [ ] Scheduled pull from public APIs (OpenAQ / WAQI / sensor.community)
- [ ] Simulated device fleet / load generator

## 2. Validate & calibrate
- [ ] Range checks, future-timestamp rejection, duplicate detection
- [ ] Stuck-sensor detection
- [ ] Drift correction against reference stations

## 3. Store
- [ ] Raw readings with retention policy
- [ ] Hourly/daily rollups

## 4. Serve (REST API)
- [ ] Current conditions near a coordinate
- [ ] Historical series
- [ ] Station metadata / rankings
- [ ] Caching + rate limiting
- [ ] OpenAPI docs

## 5. Alert
- [ ] User-defined zones/thresholds
- [ ] Threshold evaluation with hysteresis/cooldown
- [ ] Push notifications

## 6. Personal exposure
- [ ] Location ping ingestion
- [ ] Exposure dose integration over time
- [ ] Weekly report

## 7. Forecast
- [ ] Baseline persistence model
- [ ] Diurnal-profile model
- [ ] Regression model + accuracy comparison

## 8. Live view
- [ ] SSE/WebSocket real-time stream

## Cross-cutting (as-needed)
- [ ] Security (auth, device API keys)
- [ ] Observability (metrics, dashboard)
- [ ] Deployment (full Docker Compose stack, CI/CD)
