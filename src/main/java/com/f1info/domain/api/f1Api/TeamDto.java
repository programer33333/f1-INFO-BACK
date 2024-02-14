package com.f1info.domain.api.f1Api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("base")
    private String base;

    @JsonProperty("world_championships")
    private String championships;

    @JsonProperty("president")
    private String president;

    @JsonProperty("director")
    private String director;

    @JsonProperty("technical_manager")
    private String technical_manager;

    @JsonProperty("chassis")
    private String chassis;

    @JsonProperty("engine")
    private String engine;

    @JsonProperty("tyres")
    private String tyres;
}