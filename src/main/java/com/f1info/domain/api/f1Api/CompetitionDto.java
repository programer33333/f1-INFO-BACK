package com.f1info.domain.api.f1Api;

import lombok.Data;

@Data
public class CompetitionDto {

    private Long id;
    private String name;
    private LocationDto location;
}