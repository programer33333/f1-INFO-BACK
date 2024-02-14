package com.f1info.domain.history;

import com.f1info.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {

    private Long id;
    private LocalDate date;
    private String url;
    private User userId;
}