package com.f1info.service;

import com.f1info.domain.history.History;
import com.f1info.domain.user.User;
import com.f1info.exceptions.HistoryNotFoundException;
import com.f1info.exceptions.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HistoryServiceTestSuite {

    @Autowired
    HistoryService historyService;

    @Autowired
    UserService userService;
    History history;
    List<History> historyList;
    User user;

    @BeforeEach
    void setUp() {
        user = new User(123L,
                "Jan",
                "@email.com",
                "Kowalski",
                23,
                "johny",
                "password",
                historyList
        );
        history = new History(null, user, LocalDate.of(2023, 2, 3), "example/example");
        userService.saveUser(user);
        historyService.saveHistory(history);
    }

    @AfterEach
    void cleanUp() throws UserNotFoundException {
        try {
            historyService.deleteHistory(history.getId());
        } catch (HistoryNotFoundException ignored) {}
        userService.deleteUser(user.getId());
    }

    @Test
    void getHistoryTest() throws HistoryNotFoundException {
        //When
        LocalDate historyDate = historyService.getHistory(history.getId()).getDate();

        //Then
        assertEquals(LocalDate.of(2023, 2, 3), historyDate);
    }

    @Test
    void getAllHistoryListTest() {
        //When
        List<History> historyList1 = historyService.getAllHistoryList();
        Optional<History> findHistory = historyList1.stream()
                .filter(h -> h.getId().equals(history.getId()))
                .findFirst();

        //Then
        assertFalse(historyList1.isEmpty());
        assertTrue(findHistory.isPresent());
    }

    @Test
    void getHistoryByUserIdTest() throws UserNotFoundException, HistoryNotFoundException {
        //When
        List<History> historyListByUser = historyService.getHistoryByUserId(user.getId());
        Optional<History> history1 = historyListByUser.stream()
                .filter(h -> h.getId().equals(history.getId()))
                .findFirst();

        //Then
        assertFalse(historyListByUser.isEmpty());
        assertEquals(LocalDate.of(2023, 2, 3), history1.get().getDate());
    }

    @Test
    void saveHistoryTest() throws HistoryNotFoundException {
        //When
        History history1 = historyService.getHistory(history.getId());

        //Then
        assertEquals(LocalDate.of(2023, 2, 3), history1.getDate());
    }

    @Test
    void deleteHistoryTest() throws HistoryNotFoundException {
        //When
        historyService.deleteHistory(history.getId());
        Optional<History> deletedHistory = historyService.getAllHistoryList().stream()
                .filter(h -> h.getId().equals(history.getId()))
                .findFirst();

        //Then
        assertTrue(deletedHistory.isEmpty());
    }

    @Test
    void saveUserHistoryTest() throws UserNotFoundException, HistoryNotFoundException {
        //When
        historyService.saveUserHistory(user.getId(), "example/testUrl");
        Optional<History> history = historyService.getHistoryByUserId(user.getId()).stream()
                .filter(h -> h.getUrl().equals("example/testUrl"))
                .findFirst();

        //Then
        assertTrue(history.isPresent());
        assertEquals("example/testUrl", history.get().getUrl());
    }
}