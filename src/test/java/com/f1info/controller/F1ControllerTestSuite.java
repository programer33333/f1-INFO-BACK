package com.f1info.controller;

import com.f1info.client.F1Client;
import com.f1info.domain.api.f1Api.*;
import com.f1info.domain.api.f1Api.driver.DriverDto;
import com.f1info.service.HistoryService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(F1Controller.class)
public class F1ControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private F1Client f1Client;

    @MockBean
    private HistoryService historyService;


    @Test
    void testGetSeasons() throws Exception {
        // Given
        ResponseDto<Integer> seasons = new ResponseDto<>();
        seasons.setResponse(Arrays.asList(2020, 2014, 2015));

        when(f1Client.getApiResponse(any(), any(), eq(Integer.class))).thenReturn(seasons);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/f1/seasons")
                .param("userId", "23"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(2020))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(2014))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value(2015));

        verify(historyService, times(1)).saveUserHistory(23L, "f1/seasons");
    }

    @Test
    void getRacesTest() throws Exception {
        //Given
        ResponseDto<RaceDto> races = new ResponseDto<>();
        races.setResponse(Arrays.asList(new RaceDto(), new RaceDto()));

        when(f1Client.getApiResponse(any(), any(), eq(RaceDto.class))).thenReturn(races);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/f1/races")
                .param("season", "2022")
                .param("type", "race")
                .param("userId", "9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

        verify(historyService, times(1)).saveUserHistory(9L, "f1/races");
    }

    @Test
    void getRaceTest() throws Exception {
        // Given
        ResponseDto<RaceDto> race = new ResponseDto<>();
        race.setResponse(List.of(new RaceDto()));

        when(f1Client.getApiResponse(any(), any(), eq(RaceDto.class))).thenReturn(race);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/f1/race")
                        .param("season", "2022")
                        .param("type", "race")
                        .param("id", "1")
                        .param("userId", "9"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(historyService, times(1)).saveUserHistory(9L, "f1/race");
    }

    @Test
    void getDriverTest() throws Exception {
        // Given
        ResponseDto<DriverDto> driver = new ResponseDto<>();
        driver.setResponse(List.of(new DriverDto()));

        when(f1Client.getApiResponse(any(), any(), eq(DriverDto.class))).thenReturn(driver);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/f1/driver")
                        .param("search", "Hamilton")
                        .param("userId", "9"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(historyService, times(1)).saveUserHistory(9L, "f1/driver");
    }

    @Test
    void getCompetitionsTest() throws Exception {
        // Given
        ResponseDto<CompetitionDto> competitions = new ResponseDto<>();
        competitions.setResponse(Arrays.asList(new CompetitionDto(), new CompetitionDto()));

        when(f1Client.getApiResponse(any(), any(), eq(CompetitionDto.class))).thenReturn(competitions);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/f1/competitions")
                        .param("userId", "9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(historyService, times(1)).saveUserHistory(9L, "f1/competitions");
    }

    @Test
    void getCompetitionTest() throws Exception {
        // Given
        ResponseDto<CompetitionDto> competition = new ResponseDto<>();
        competition.setResponse(List.of(new CompetitionDto()));

        when(f1Client.getApiResponse(any(), any(), eq(CompetitionDto.class))).thenReturn(competition);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/f1/competition")
                        .param("id", "123")
                        .param("userId", "9"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(historyService, times(1)).saveUserHistory(9L, "f1/competition");
    }

    @Test
    void getCircuitsTest() throws Exception {
        // Given
        ResponseDto<CircuitDto> circuits = new ResponseDto<>();
        circuits.setResponse(Arrays.asList(new CircuitDto(), new CircuitDto()));

        when(f1Client.getApiResponse(any(), any(), eq(CircuitDto.class))).thenReturn(circuits);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/f1/circuits")
                        .param("userId", "9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(historyService, times(1)).saveUserHistory(9L, "f1/circuits");
    }

    @Test
    void getCircuitTest() throws Exception {
        // Given
        ResponseDto<CircuitDto> circuit = new ResponseDto<>();
        circuit.setResponse(List.of(new CircuitDto()));

        when(f1Client.getApiResponse(any(), any(), eq(CircuitDto.class))).thenReturn(circuit);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/f1/circuit")
                        .param("id", "456")
                        .param("userId", "9"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(historyService, times(1)).saveUserHistory(9L, "f1/circuit");
    }

    @Test
    void getTeamsTest() throws Exception {
        // Given
        ResponseDto<TeamDto> teams = new ResponseDto<>();
        teams.setResponse(Arrays.asList(new TeamDto(), new TeamDto()));

        when(f1Client.getApiResponse(any(), any(), eq(TeamDto.class))).thenReturn(teams);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/f1/teams")
                        .param("userId", "9"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(historyService, times(1)).saveUserHistory(9L, "f1/teams");
    }

    @Test
    void getTeamTest() throws Exception {
        // Given
        ResponseDto<TeamDto> team = new ResponseDto<>();
        team.setResponse(List.of(new TeamDto()));

        when(f1Client.getApiResponse(any(), any(), eq(TeamDto.class))).thenReturn(team);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/f1/team")
                        .param("id", "789")
                        .param("userId", "9"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(historyService, times(1)).saveUserHistory(9L, "f1/team");
    }
}