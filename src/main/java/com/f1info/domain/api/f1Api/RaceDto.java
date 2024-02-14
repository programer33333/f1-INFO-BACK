package com.f1info.domain.api.f1Api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RaceDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("competition")
    private CompetitionDto competition;

    @JsonProperty("circuit")
    private CircuitDto circuit;

    @JsonProperty("season")
    private int season;

    @JsonProperty("date")
    private String date;
}