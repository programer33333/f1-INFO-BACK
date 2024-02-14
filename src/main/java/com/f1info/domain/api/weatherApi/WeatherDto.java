package com.f1info.domain.api.weatherApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {

    @JsonProperty("date")
    private String date;

    @JsonProperty("weather")
    private String weather;

    @JsonProperty("temperature")
    private double temperature;

    @JsonProperty("cloud_cover")
    private int cloudCover;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;
}