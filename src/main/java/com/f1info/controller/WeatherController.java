package com.f1info.controller;

import com.f1info.client.WeatherClient;
import com.f1info.config.EndpointConstants;
import com.f1info.domain.api.f1Api.RaceDto;
import com.f1info.domain.api.weatherApi.WeatherDto;
import com.f1info.service.HistoryService;
import com.f1info.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/weather")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WeatherController {

    private final WeatherClient weatherClient;
    private final HistoryService historyService;
    private final F1Controller f1Controller;
    private final WeatherService weatherService;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);


    @GetMapping
    public ResponseEntity<WeatherDto> getWeather(@RequestParam String season, String raceId, Long userId) {
        try {
            RaceDto raceDto = f1Controller.getRace(season, "race", raceId, userId).getBody();
            Map<String, String> weatherParams = weatherService.prepareWeatherParams(raceDto);

            List<WeatherDto> weatherListByHour = weatherClient
                    .getResponse(EndpointConstants.WEATHER, weatherParams)
                    .getWeatherByHourList();

            historyService.saveUserHistory(userId, "weather");

            return ResponseEntity.ok(weatherService.findWeather(raceDto, weatherListByHour).get());
        } catch (Exception e) {
            LOGGER.error("An error occurred while processing weather request: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}