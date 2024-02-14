package com.f1info.domain.user;

import com.f1info.domain.history.History;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String surname;
    private int age;
    private String login;
    private String password;

    @JsonIgnore
    private List<History> history;
}