package com.f1info.service;

import com.f1info.domain.history.History;
import com.f1info.domain.user.User;
import com.f1info.exceptions.HistoryNotFoundException;
import com.f1info.exceptions.UserNotFoundException;
import com.f1info.repository.HistoryRepository;
import com.f1info.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public History getHistory(final Long historyId) throws HistoryNotFoundException {
        return historyRepository.findById(historyId).orElseThrow(HistoryNotFoundException::new);
    }

    public List<History> getAllHistoryList() {
        return historyRepository.findAll();
    }

    public List<History> getHistoryByUserId(Long userId) throws UserNotFoundException, HistoryNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<History> historyList = historyRepository.findByUserId(user);

        if (historyList.isEmpty()) {
            throw new HistoryNotFoundException();
        }
        return historyList;
    }

    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    public void deleteHistory(final Long id) throws HistoryNotFoundException {
        historyRepository.findById(id).orElseThrow(HistoryNotFoundException::new);
        historyRepository.deleteById(id);
    }

    public void saveUserHistory(Long userId, String url) throws UserNotFoundException {
        History history = new History();
        User user = userService.getUser(userId);
        history.setUserId(user);
        history.setDate(LocalDate.now());
        history.setUrl(url);
        saveHistory(history);
    }
}