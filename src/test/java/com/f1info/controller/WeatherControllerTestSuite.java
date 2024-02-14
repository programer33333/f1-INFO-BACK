package com.f1info.controller;

import com.f1info.client.WeatherClient;
import com.f1info.domain.api.f1Api.RaceDto;
import com.f1info.domain.api.weatherApi.WeatherDto;
import com.f1info.domain.api.weatherApi.WeatherResponseDto;
import com.f1info.service.HistoryService;
import com.f1info.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private WeatherClient weatherClient;

    @MockBean
    private HistoryService historyService;

    @MockBean
    private F1Controller f1Controller;

    @Test
    void getWeatherTest() throws Exception {
        //Given
        when(f1Controller.getRace(any(), any(), any(), any())).thenReturn(new ResponseEntity<>(new RaceDto(), HttpStatus.OK) );
        when(weatherService.prepareWeatherParams(any())).thenReturn(new HashMap<>());
        when(weatherClient.getResponse(any(), any())).thenReturn(new WeatherResponseDto());
        when(weatherService.findWeather(any(), any())).thenReturn(Optional.of(new WeatherDto()));

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/weather")
                .param("season", "2022")
                .param("raceId", "1488")
                .param("userId", "302"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").exists());

        verify(historyService, times(1)).saveUserHistory(302L, "weather");
        verify(f1Controller, times(1)).getRace(any(), any(), any(), any());
        verify(weatherService, times(1)).prepareWeatherParams(any());
        verify(weatherClient, times(1)).getResponse(any(), any());
        verify(weatherService, times(1)).findWeather(any(), any());
    }
}
