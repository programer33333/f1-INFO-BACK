package com.f1info.service;

import com.f1info.config.CityCoordinates;
import com.f1info.domain.api.f1Api.CompetitionDto;
import com.f1info.domain.api.f1Api.LocationDto;
import com.f1info.domain.api.f1Api.RaceDto;
import com.f1info.domain.api.weatherApi.WeatherDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTestSuite {

    @Test
    void findWeatherTest() {
        //Given
        WeatherService weatherService = new WeatherService();
        RaceDto raceDto = new RaceDto();
        raceDto.setDate("2024-02-08T12:00:00+00:00");
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setDate("2024-02-08T12:00:00");
        List<WeatherDto> weatherListByHour = List.of(weatherDto);

        //When
        WeatherDto result = weatherService.findWeather(raceDto, weatherListByHour).get();

        //Then
        assertEquals(result.getDate(), "2024-02-08T12:00:00");
    }

    @Test
    void testPrepareWeatherParams() {
        //Given
        WeatherService weatherService = new WeatherService();
        RaceDto raceDto = new RaceDto();
        raceDto.setDate("2024-02-08T12:00:00+00:00");
        CompetitionDto competitionDto = new CompetitionDto();
        LocationDto locationDto = new LocationDto();
        locationDto.setCity("Example City");
        competitionDto.setLocation(locationDto);
        raceDto.setCompetition(competitionDto);

        //When
        Map<String, String> result = weatherService.prepareWeatherParams(raceDto);

        //Then
        assertEquals("2024-02-08", result.get("date"));
    }

    @Test
    void testSetCoordinates() {
        // Given
        CityCoordinates cityCoordinates = new CityCoordinates();

        // When
        cityCoordinates.setCoordinates("Melbourne");

        // Then
        assertEquals("-37.8140000", cityCoordinates.getLatitude());
        assertEquals("144.9633200", cityCoordinates.getLongitude());
    }
}