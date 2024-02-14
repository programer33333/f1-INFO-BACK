package com.f1info.service;

import com.f1info.domain.history.History;
import com.f1info.domain.user.User;
import com.f1info.exceptions.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTestSuite {

    @Autowired
    UserService userService;
    User user;
    List<History> historyList;

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
        userService.saveUser(user);
    }

    @AfterEach
    void cleanUp() {
        try {
            userService.deleteUser(user.getId());
        } catch (UserNotFoundException ignore) {}
    }

    @Test
    void getUserTest() throws UserNotFoundException {
        //When
        User testUser = userService.getUser(user.getId());

        //Then
        assertEquals("password", testUser.getPassword());
    }

    @Test
    void getUsersTest() {
        //When
        List<User> userList = userService.getUsers();
        Optional<User> findedUser = userList.stream()
                .filter(u -> u.getLogin().equals("johny"))
                .findFirst();

        //Then
        assertTrue(findedUser.isPresent());
    }

    @Test
    void saveUserTest() throws UserNotFoundException {
        //When
        User testUser = userService.getUser(user.getId());

        //Then
        assertEquals("Kowalski", testUser.getSurname());
    }

    @Test
    void deleteUserTest() {
        //When
        Optional<User> deletedUser = userService.getUsers().stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst();

        //Then
        assertTrue(deletedUser.isPresent());
    }

    @Test
    void editPasswordTest() throws UserNotFoundException {
        //When
        userService.editPassword("newPassword", user.getId());
        String password = userService.getUser(user.getId()).getPassword();

        //Then
        assertEquals("newPassword", password);
    }

    @Test
    void editAgeTest() throws UserNotFoundException {
        //When
        userService.editAge(22, user.getId());
        int age = userService.getUser(user.getId()).getAge();

        //Then
        assertEquals(22, age);
    }

    @Test
    void editEmailTest() throws UserNotFoundException {
        //When
        userService.editEmail("newEmail", user.getId());
        String email = userService.getUser(user.getId()).getEmail();

        //Then
        assertEquals("newEmail", email);
    }

    @Test
    void editNameTest() throws UserNotFoundException {
        //When
        userService.editName("Tomas", user.getId());
        String name = userService.getUser(user.getId()).getName();

        //Then
        assertEquals("Tomas", name);
    }

    @Test
    void editSurnameTest() throws UserNotFoundException {
        //When
        userService.editSurname("NewSurname", user.getId());
        String surname = userService.getUser(user.getId()).getSurname();

        //Then
        assertEquals("NewSurname", surname);
    }

    @Test
    void isUserAlreadyExistTest() {
        //When & Then
        assertTrue(userService.isLoginAlreadyExist(user.getLogin()));
    }
}