package com.f1info.service;

import com.f1info.config.CityCoordinates;
import com.f1info.domain.api.f1Api.RaceDto;
import com.f1info.domain.api.weatherApi.WeatherDto;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WeatherService {

    public Optional<WeatherDto> findWeather(RaceDto raceDto, List<WeatherDto> weatherListByHour) {
        String date = raceDto.getDate();
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String dateWithoutOffset = offsetDateTime.toLocalDateTime().toString() + ":00";

        return weatherListByHour.stream()
                .filter(weatherDto -> weatherDto.getDate().equals(dateWithoutOffset))
                .findFirst();
    }

    public Map<String, String> prepareWeatherParams(RaceDto raceDto) {
        String place = raceDto.getCompetition().getLocation().getCity();
        CityCoordinates coordinates = new CityCoordinates();
        coordinates.setCoordinates(place);
        String lat = coordinates.getLatitude();
        String lon = coordinates.getLongitude();

        Map<String, String> weatherParams = new HashMap<>();
        weatherParams.put("date", raceDto.getDate().substring(0, 10));
        weatherParams.put("lat", lat);
        weatherParams.put("lon", lon);
        weatherParams.put("units", "metric");

        return weatherParams;
    }
}