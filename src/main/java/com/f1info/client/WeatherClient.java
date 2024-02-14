package com.f1info.client;

import com.f1info.config.ApiConfig;
import com.f1info.domain.api.weatherApi.WeatherResponseDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Builder
@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final ApiConfig apiConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class);

    public WeatherResponseDto getResponse(String endpoint, Map<String, String> queryParams) {
        try {
            ResponseEntity<WeatherResponseDto> response = restTemplate.exchange(
                    createUrl(endpoint, queryParams),
                    HttpMethod.GET,
                    getWeatherEntity(),
                    WeatherResponseDto.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            LOGGER.error("RestTemplate error: " + e.getMessage(), e);
            return null;
        }
    }

    public HttpEntity<?> getWeatherEntity() {
        HttpHeaders header = new HttpHeaders();
        header.set(apiConfig.getRapidKeyName(), apiConfig.getRapidKey());
        return new HttpEntity<>(header);
    }

    public URI createUrl(String endpoint, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(apiConfig.getWeatherApiEndpoint() + endpoint);

        queryParams.forEach(builder::queryParam);

        return builder.build()
                .encode()
                .toUri();
    }
}