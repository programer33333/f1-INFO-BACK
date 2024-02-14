package com.f1info.mapper;

import com.f1info.domain.history.History;
import com.f1info.domain.user.User;
import com.f1info.domain.user.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
public class UserMapperTestSuite {

    @Autowired
    UserMapper userMapper;
    User user;
    List<History> historyList;

    @BeforeEach
    void setup() {
        user = new User(
                123L,
                "Jan",
                "@email.com",
                "Kowalski",
                23,
                "jann",
                "password",
                historyList
        );

        historyList = new ArrayList<>();
    }
    @Test
    void mapToUserDtoTest() {
        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(123L, userDto.getId());
        assertEquals("Jan", userDto.getName());
        assertEquals("password", userDto.getPassword());
    }

    @Test
    void mapToUserDtoListTest() {
        //Given
        List userList = new ArrayList();
        userList.add(user);

        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);

        //Then
        assertFalse(userDtoList.isEmpty());
        assertEquals(123L, userDtoList.get(0).getId());
        assertEquals("Kowalski", userDtoList.get(0).getSurname());
        assertEquals("jann", userDtoList.get(0).getLogin());
    }

    @Test
    void mapToUserTest() {
        //Given
        UserDto userDto = new UserDto(
                12443L,
                "Johny",
                "@email.com",
                "Kowalski",
                23,
                "jann",
                "password",
                historyList
        );

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertEquals(12443L, userDto.getId());
        assertEquals("Johny", userDto.getName());
        assertEquals("password", userDto.getPassword());
    }
}