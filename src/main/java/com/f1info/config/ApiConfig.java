package com.f1info.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiConfig {

    @Value("${f1.api.endpoint}")
    private String f1ApiEndpoint;

    @Value("${rapid.app.key}")
    private String rapidKey;

    @Value("${rapid.app.key.name}")
    private String rapidKeyName;

    @Value("${weather.api.endpoint}")
    private String weatherApiEndpoint;
}