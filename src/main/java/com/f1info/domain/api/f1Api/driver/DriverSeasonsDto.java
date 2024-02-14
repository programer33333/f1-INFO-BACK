package com.f1info.domain.api.f1Api.driver;

import com.f1info.domain.api.f1Api.TeamDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DriverSeasonsDto {

    @JsonProperty("season")
    private int season;

    @JsonProperty("team")
    private DriverTeamsDto team;
}