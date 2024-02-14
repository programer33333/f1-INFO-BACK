package com.f1info.domain.api.f1Api.driver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("birthdate")
    private LocalDate birthdate;

    @JsonProperty("world_championships")
    private Long championships;

    @JsonProperty("career_points")
    private Double points;

    @JsonProperty("teams")
    private List<DriverSeasonsDto> seasonsList;
}