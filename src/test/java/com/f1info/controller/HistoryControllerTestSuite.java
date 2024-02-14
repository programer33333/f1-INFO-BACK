package com.f1info.controller;

import com.f1info.domain.history.History;
import com.f1info.domain.history.HistoryDto;
import com.f1info.domain.user.User;
import com.f1info.exceptions.HistoryNotFoundException;
import com.f1info.mapper.HistoryMapper;
import com.f1info.service.HistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class HistoryControllerTestSuite {

    @InjectMocks
    private HistoryController historyController;
    @Mock
    private HistoryService historyService;
    @Mock
    private HistoryMapper historyMapper;

    @Test
    void getHistoryListTest() {
        // Given
        List<History> historyList = Arrays.asList(new History());
        List<HistoryDto> historyDtoList = Arrays.asList(new HistoryDto());
        when(historyService.getAllHistoryList()).thenReturn(historyList);
        when(historyMapper.mapToHistoryDtoList(historyList)).thenReturn(historyDtoList);

        // When
        ResponseEntity<List<HistoryDto>> response = historyController.getHistoryList();

        // Then
        verify(historyService, times(1)).getAllHistoryList();
        verify(historyMapper, times(1)).mapToHistoryDtoList(historyList);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getHistoryByIdTest() throws HistoryNotFoundException {
        // Given
        History history = new History();
        HistoryDto historyDto = new HistoryDto();
        when(historyService.getHistory(history.getId())).thenReturn(history);
        when(historyMapper.mapToHistoryDto(history)).thenReturn(historyDto);

        // When
        ResponseEntity<HistoryDto> response = historyController.getHistoryById(history.getId());

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(historyDto, response.getBody());
    }

    @Test
    void getHistoryByUserIdTest() throws Exception {
        // Given
        List<History> historyList = Arrays.asList(new History());
        List<HistoryDto> historyDtoList = Arrays.asList(new HistoryDto());
        User user = new User();
        when(historyService.getHistoryByUserId(user.getId())).thenReturn(historyList);
        when(historyMapper.mapToHistoryDtoList(historyList)).thenReturn(historyDtoList);

        // When
        ResponseEntity<List<HistoryDto>> response = historyController.getHistoryByUserId(user.getId());

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void deleteHistoryTest() throws HistoryNotFoundException {
        //Given
        History history = new History();
        doNothing().when(historyService).deleteHistory(history.getId());

        // When
        ResponseEntity<String> response = historyController.deleteHistory(history.getId());

        // Then
        verify(historyService, times(1)).deleteHistory(history.getId());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("History with ID: " + history.getId() + " has been deleted.", response.getBody());
    }
}