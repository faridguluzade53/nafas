# Nafas — Progress

Tracking against java-spring-roadmap.md. Update at the end of every session (Rule 3).

## Status legend
- Not started
- In progress
- Gate passed

## Phase 0 — Setup & baseline
Status: In progress
- [x] JDK, IntelliJ, Docker Desktop, Maven installed
- [x] Repo initialized, .gitignore in place
- [ ] MIT licence
- [ ] README with real problem statement (currently a bare stub)
- [ ] Branch protection on main / PR workflow
- [ ] ADR template in /docs/adr/
- [ ] Gate 0 — NOT attempted

## Phase 1 — Core Java that actually matters
Status: Not started
Note: running in parallel via Udemy Java Masterclass — check course progress before attempting Gate 1, some of this may already be covered.

## Phase 2 — Concurrency & the JVM
Status: Not started

## Phase 3 — Spring Core & Boot internals
Status: In progress (out of sequence)
- [x] Spring Boot app running (Maven, Java 21, Spring Boot 4.1.1)
- [x] GET /api/health endpoint
- [ ] Domain model converted from Phase 1 (blocked on Phase 1)
- [ ] @ConfigurationProperties for thresholds/limits/keys
- [ ] dev/prod profiles
- [ ] Custom autoconfiguration + starter
- [ ] Gate 3 — NOT attempted
Note: this is infrastructure scaffolding, not a real Phase 3 attempt. Jumped ahead for practical reasons (needed a running app before most other work made sense).

## Phase 4 — REST API design
Status: Not started

## Phase 5 — Persistence: JPA, Hibernate, SQL
Status: In progress (out of sequence)
- [x] Docker Compose: TimescaleDB (pg17) running locally
- [x] Flyway migration, sensor_reading verified as a real hypertable
- [x] SensorReading entity (@IdClass) + Spring Data repository
- [ ] Full schema (stations, users, devices, alert_rules, alert_events, location_pings)
- [ ] Rollup tables / continuous aggregates
- [ ] Radius/spatial queries
- [ ] N+1 query-count regression test
- [ ] Gate 5 — NOT attempted
Note: single-table proof of connectivity only. Core JPA/Hibernate concepts (N+1, fetch types, transactions, locking, indexing) not yet covered.

## Phase 6 — Security
Status: Not started

## Phase 7 — Testing & quality
Status: Barely started
- [x] One Testcontainers-based repository test (SensorReadingRepositoryTest)
- [ ] Everything else

## Phase 8 — Async, messaging & caching
Status: Not started

## Phase 9 — DevOps & observability
Status: Not started

## Phase 10 — Scale, depth, and finishing
Status: Not started

---

## Honest read
No gates have been passed yet. Phase 3 and 5 progress above is real but partial — 
minimum scaffolding to unblock further work, not the phase deliverables. Don't let 
"app boots" or "one table exists" read as done.

Recommended next real step: attempt Gate 0 (~30-60 min, no help, no lookups). 
It tells you honestly whether Phase 1 can be skipped, which matters for how you 
split time against the Udemy course.
