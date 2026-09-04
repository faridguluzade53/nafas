package com.nafas.ingest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// OpenAQ v3 response shapes, trimmed to the fields this client reads.
// ignoreUnknown = true because the real payloads carry many more fields
// (coordinates, meta, licenses, ...) that we don't need.

@JsonIgnoreProperties(ignoreUnknown = true)
record OpenAqLocationsResponse(List<OpenAqLocationResult> results) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record OpenAqLocationResult(List<OpenAqSensorInfo> sensors) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record OpenAqSensorInfo(Long id, OpenAqParameterInfo parameter) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record OpenAqParameterInfo(String name) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record OpenAqLatestResponse(List<OpenAqLatestResult> results) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record OpenAqLatestResult(OpenAqDatetime datetime, Double value, Long sensorsId) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record OpenAqDatetime(String utc) {
}
