package com.f1info.domain.api.weatherApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDto {

    @JsonProperty("data")
    private List<WeatherDto> weatherByHourList;
}
