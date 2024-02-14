package com.f1info.controller;

import com.f1info.client.F1Client;
import com.f1info.config.EndpointConstants;
import com.f1info.domain.api.f1Api.CircuitDto;
import com.f1info.domain.api.f1Api.CompetitionDto;
import com.f1info.domain.api.f1Api.RaceDto;
import com.f1info.domain.api.f1Api.TeamDto;
import com.f1info.domain.api.f1Api.driver.DriverDto;
import com.f1info.exceptions.UserNotFoundException;
import com.f1info.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("v1/f1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class F1Controller {

    private final F1Client f1Client;
    private final HistoryService historyService;

    @GetMapping("seasons")
    public ResponseEntity<List<Integer>> getSeasons(@RequestParam Long userId) throws UserNotFoundException {
        List<Integer> response = f1Client.getApiResponse(EndpointConstants.SEASONS, null, Integer.class).getResponse();
        historyService.saveUserHistory(userId, "f1/seasons");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("races")
    public ResponseEntity<List<RaceDto>> getRaces(@RequestParam String season, String type, Long userId) throws UserNotFoundException {
        HashMap<String, String> params = new HashMap<>();
        params.put("season", season);
        params.put("type", type);
        List<RaceDto> response = f1Client.getApiResponse(EndpointConstants.RACES, params, RaceDto.class).getResponse();
        historyService.saveUserHistory(userId, "f1/races");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("race")
    public ResponseEntity<RaceDto> getRace(@RequestParam String season, String type, String id, Long userId) throws UserNotFoundException {
        HashMap<String, String> params = new HashMap<>();
        params.put("season", season);
        params.put("type", type);
        params.put("id", id);
        RaceDto response = f1Client.getApiResponse(EndpointConstants.RACES, params, RaceDto.class).getResponse().get(0);
        historyService.saveUserHistory(userId, "f1/race");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("driver")
    public ResponseEntity<DriverDto> getDriver(@RequestParam String search, Long userId) throws UserNotFoundException {
        HashMap<String, String> params = new HashMap<>();
        params.put("search", search);
        DriverDto response = f1Client.getApiResponse(EndpointConstants.DRIVERS, params, DriverDto.class).getResponse().get(0);
        historyService.saveUserHistory(userId, "f1/driver");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("competitions")
    public ResponseEntity<List<CompetitionDto>> getCompetitions(@RequestParam Long userId) throws UserNotFoundException {
        List<CompetitionDto> response = f1Client.getApiResponse(EndpointConstants.COMPETITIONS, null, CompetitionDto.class).getResponse();
        historyService.saveUserHistory(userId, "f1/competitions");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("competition")
    public ResponseEntity<CompetitionDto> getCompetition(@RequestParam String id, Long userId) throws UserNotFoundException {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        CompetitionDto response = f1Client.getApiResponse(EndpointConstants.COMPETITIONS, params, CompetitionDto.class).getResponse().get(0);
        historyService.saveUserHistory(userId, "f1/competition");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("circuits")
    public ResponseEntity<List<CircuitDto>> getCircuits(@RequestParam Long userId) throws UserNotFoundException {
        List<CircuitDto> response = f1Client.getApiResponse(EndpointConstants.CIRCUITS, null, CircuitDto.class).getResponse();
        historyService.saveUserHistory(userId, "f1/circuits");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("circuit")
    public ResponseEntity<CircuitDto> getCircuit(@RequestParam String id, Long userId) throws UserNotFoundException {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        CircuitDto response = f1Client.getApiResponse(EndpointConstants.CIRCUITS, params, CircuitDto.class).getResponse().get(0);
        historyService.saveUserHistory(userId, "f1/circuit");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("teams")
    public ResponseEntity<List<TeamDto>> getTeams(@RequestParam Long userId) throws UserNotFoundException {
        List<TeamDto> response = f1Client.getApiResponse(EndpointConstants.TEAMS, null, TeamDto.class).getResponse();
        historyService.saveUserHistory(userId, "f1/teams");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("team")
    public ResponseEntity<TeamDto> getTeam(@RequestParam String id, Long userId) throws UserNotFoundException {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        TeamDto response = f1Client.getApiResponse(EndpointConstants.TEAMS, params, TeamDto.class).getResponse().get(0);
        historyService.saveUserHistory(userId, "f1/team");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}