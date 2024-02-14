package com.f1info.mapper;

import com.f1info.domain.history.History;
import com.f1info.domain.history.HistoryDto;
import com.f1info.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class HistoryMapperTestSuite {

    @Autowired
    HistoryMapper historyMapper;

    @Test
    void mapToHistoryDtoTest() {
        //Given
        User user = new User();
        History history = new History(1234L, user, LocalDate.of(2023, 2, 3), "example/example");

        //When
        HistoryDto historyDto = historyMapper.mapToHistoryDto(history);

        //Then
        assertEquals(1234L, historyDto.getId());
        assertEquals(LocalDate.of(2023, 2, 3), historyDto.getDate());
        assertEquals("example/example", historyDto.getUrl());
    }

    @Test
    void mapToHistoryDtoListTest() {
        //Given
        User user = new User();
        History history1 = new History(1234L, user, LocalDate.of(2023, 9, 9), "example.pl");
        History history2 = new History(3211L, user, LocalDate.of(2021, 1, 11), "example123");
        History history3 = new History(6543L, user, LocalDate.of(2024, 7, 5), "example/");

        List historyList = new ArrayList();
        historyList.add(history1);
        historyList.add(history2);
        historyList.add(history3);

        //When
        List<HistoryDto> historyDtoList = historyMapper.mapToHistoryDtoList(historyList);

        //Then
        assertEquals(1234L, historyDtoList.get(0).getId());
        assertEquals(3211L, historyDtoList.get(1).getId());
        assertEquals(6543L, historyDtoList.get(2).getId());
    }
}