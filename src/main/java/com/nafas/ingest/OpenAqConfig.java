package com.nafas.ingest;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(OpenAqProperties.class)
public class OpenAqConfig {

	@Bean
	public WebClient openAqWebClient(OpenAqProperties properties) {
		return WebClient.builder()
				.baseUrl(properties.getBaseUrl())
				.defaultHeader("X-API-Key", properties.getApiKey())
				.build();
	}

}
