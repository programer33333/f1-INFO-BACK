package com.f1info.controller;

import com.f1info.domain.history.History;
import com.f1info.domain.history.HistoryDto;
import com.f1info.exceptions.HistoryNotFoundException;
import com.f1info.mapper.HistoryMapper;
import com.f1info.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final HistoryMapper historyMapper;

    @GetMapping("all")
    public ResponseEntity<List<HistoryDto>> getHistoryList() {
        List<History> historyList = historyService.getAllHistoryList();
        return ResponseEntity.ok(historyMapper.mapToHistoryDtoList(historyList));
    }

    @GetMapping
    public ResponseEntity<HistoryDto> getHistoryById(@RequestParam Long historyId) throws HistoryNotFoundException {
        History history = historyService.getHistory(historyId);
        return ResponseEntity.ok(historyMapper.mapToHistoryDto(history));
    }

    @GetMapping("userHistory")
    public ResponseEntity<List<HistoryDto>> getHistoryByUserId(@RequestParam Long userId) throws Exception {
        List<History> historyList = historyService.getHistoryByUserId(userId);
        return ResponseEntity.ok(historyMapper.mapToHistoryDtoList(historyList));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHistory(@RequestParam Long id) throws HistoryNotFoundException {
        historyService.deleteHistory(id);
        return new ResponseEntity<>("History with ID: " + id +  " has been deleted.", HttpStatus.OK);
    }
}