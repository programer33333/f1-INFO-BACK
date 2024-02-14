package com.f1info.mapper;

import com.f1info.domain.user.User;
import com.f1info.domain.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getSurname(),
                userDto.getAge(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getHistory()
                );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getSurname(),
                user.getAge(),
                user.getLogin(),
                user.getPassword(),
                user.getHistory()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}