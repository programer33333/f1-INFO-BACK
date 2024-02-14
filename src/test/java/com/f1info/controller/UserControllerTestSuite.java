package com.f1info.controller;

import com.f1info.domain.history.History;
import com.f1info.domain.user.User;
import com.f1info.domain.user.UserDto;
import com.f1info.exceptions.UserDuplicateException;
import com.f1info.exceptions.UserNotFoundException;
import com.f1info.mapper.UserMapper;
import com.f1info.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserControllerTestSuite {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserService userService;
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        List<History> historyList = new ArrayList<>();

        user = new User(123L,
                "Jan",
                "@email.com",
                "Kowalski",
                23,
                "johny",
                "password",
                historyList
        );

        userDto = new UserDto(123L,
                "Jan",
                "@email.com",
                "Kowalski",
                23,
                "johny",
                "password",
                historyList
        );
    }

    @Test
    void createUserTest() throws UserDuplicateException {
        //Given
        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(userService.isLoginAlreadyExist("johny")).thenReturn(false);
        when(userService.saveUser(user)).thenReturn(user);

        //When
        ResponseEntity<UserDto> response = userController.createUser(userDto);

        //Then
        verify(userService, times(1)).isLoginAlreadyExist("johny");
        verify(userMapper, times(1)).mapToUser(userDto);
        verify(userMapper, times(1)).mapToUserDto(user);
        verify(userService, times(1)).saveUser(user);
        assert response.getStatusCode().equals(HttpStatus.CREATED);
        assert response.getBody() != null;
        assert response.getBody().getLogin().equals("johny");
    }

    @Test
    void getUserTest() throws UserNotFoundException {
        //Given
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(userService.getUser(user.getId())).thenReturn(user);

        //When
        ResponseEntity<UserDto> response = userController.getUser(user.getId());

        //Then
        verify(userService, times(1)).getUser(user.getId());
        verify(userMapper, times(1)).mapToUserDto(user);
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody() != null;
        assert response.getBody().getId().equals(user.getId());
        assert response.getBody().getLogin().equals("johny");
    }

    @Test
    void getUsersTest() {
        // Given
        List<User> userList = new ArrayList<>();
        userList.add(user);
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);
        when(userService.getUsers()).thenReturn(userList);
        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);

        // When
        ResponseEntity<List<UserDto>> response = userController.getUsers();

        // Then
        verify(userService, times(1)).getUsers();
        verify(userMapper, times(1)).mapToUserDtoList(userList);
        assert response.getBody() != null;
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody().get(0).getLogin().equals("johny");
    }

    @Test
    void getCountTest() {
        //Given
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userService.getUsers()).thenReturn(userList);

        //When
        ResponseEntity<Integer> response = userController.usersCount();

        //Then
        verify(userService, times(1)).getUsers();
        assert response.getBody() != null;
        assert response.getBody().equals(1);
    }

    @Test
    void deleteUserTest() throws UserNotFoundException {
        //Given
        doNothing().when(userService).deleteUser(user.getId());

        //When
        ResponseEntity<String> response = userController.deleteUser(user.getId());

        //Then
        verify(userService, times(1)).deleteUser(user.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("User with ID: " + user.getId() + " has been deleted.", response.getBody());
    }

    @Test
    void editPasswordTest() throws UserNotFoundException {
        // Given
        when(userService.editPassword("newPassword", user.getId())).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        // When
        ResponseEntity<UserDto> response = userController.editPassword("newPassword", user.getId());

        // Then
        verify(userService, times(1)).editPassword("newPassword", user.getId());
        verify(userMapper, times(1)).mapToUserDto(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void editAgeTest() throws UserNotFoundException {
        // Given
        when(userService.editAge(35, user.getId())).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        // When
        ResponseEntity<UserDto> response = userController.editAge("35", user.getId());

        // Then
        verify(userService, times(1)).editAge(35, user.getId());
        verify(userMapper, times(1)).mapToUserDto(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void editEmailTest() throws UserNotFoundException {
        // Given
        when(userService.editEmail("new@example.com", user.getId())).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        // When
        ResponseEntity<UserDto> response = userController.editEmail("new@example.com", user.getId());

        // Then
        verify(userService, times(1)).editEmail("new@example.com", user.getId());
        verify(userMapper, times(1)).mapToUserDto(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void editNameTest() throws UserNotFoundException {
        // Given
        when(userService.editName("NewName", user.getId())).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        // When
        ResponseEntity<UserDto> response = userController.editName("NewName", user.getId());

        // Then
        verify(userService, times(1)).editName("NewName", user.getId());
        verify(userMapper, times(1)).mapToUserDto(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto, response.getBody());
    }

    @Test
    void editSurnameTest() throws UserNotFoundException {
        // Given
        when(userService.editSurname("NewSurname", user.getId())).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        // When
        ResponseEntity<UserDto> response = userController.editSurname("NewSurname", user.getId());

        // Then
        verify(userService, times(1)).editSurname("NewSurname", user.getId());
        verify(userMapper, times(1)).mapToUserDto(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userDto, response.getBody());
    }
}