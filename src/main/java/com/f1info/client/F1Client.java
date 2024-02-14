package com.f1info.client;

import com.f1info.config.ApiConfig;
import com.f1info.domain.api.f1Api.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;

@Component
@RequiredArgsConstructor
public class F1Client {

    private final RestTemplate restTemplate;
    private final ApiConfig apiConfig;
    private final ResponseConverter responseConverter;
    private static final Logger LOGGER = LoggerFactory.getLogger(F1Client.class);

    public <T> ResponseDto<T> getApiResponse(String endpoint, Map<String, String> queryParams, Class<T> responseType) {
        try {
            ResponseEntity<ResponseDto> response = restTemplate.exchange(
                    createUrl(endpoint, queryParams),
                    HttpMethod.GET,
                    getF1Entity(),
                    ResponseDto.class
            );
            return responseConverter.convertResponse(response, responseType);
        } catch (RestClientException e) {
            LOGGER.error("RestTemplate error: " + e.getMessage(), e);
            return null;
        }
    }

    public HttpEntity<?> getF1Entity() {
        HttpHeaders header = new HttpHeaders();
        header.set(apiConfig.getRapidKeyName(), apiConfig.getRapidKey());
        return new HttpEntity<>(header);
    }

    public URI createUrl(String endpoint, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(apiConfig.getF1ApiEndpoint() + endpoint);

        if (queryParams != null) {
            queryParams.forEach(builder::queryParam);
        }

        return builder.build()
                .encode()
                .toUri();
    }
}