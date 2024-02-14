package com.f1info.mapper;

import com.f1info.domain.history.History;
import com.f1info.domain.history.HistoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryMapper {

    public HistoryDto mapToHistoryDto(final History history) {
        return new HistoryDto(
                history.getId(),
                history.getDate(),
                history.getUrl(),
                history.getUserId()
        );
    }

    public List<HistoryDto> mapToHistoryDtoList(final List<History> historyList) {
        return historyList.stream()
                .map(this::mapToHistoryDto)
                .collect(Collectors.toList());
    }
}