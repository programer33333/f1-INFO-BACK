package com.f1info.controller;

import com.f1info.domain.user.User;
import com.f1info.domain.user.UserDto;
import com.f1info.exceptions.UserDuplicateException;
import com.f1info.exceptions.UserNotFoundException;
import com.f1info.mapper.UserMapper;
import com.f1info.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws UserDuplicateException {
        String login = userDto.getLogin();

        if(userService.isLoginAlreadyExist(login)) {
            throw new UserDuplicateException();
        }

        User user = userMapper.mapToUser(userDto);
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(userMapper.mapToUserDto(createdUser), HttpStatus.CREATED);
    }

    @GetMapping("user")
    public ResponseEntity<UserDto> getUser(@RequestParam Long id) throws UserNotFoundException {
        User user = userService.getUser(id);
        return new ResponseEntity<>(userMapper.mapToUserDto(user), HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping
    public ResponseEntity<Integer> usersCount() {
        return new ResponseEntity<>(userService.getUsers().size(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<>("User with ID: " + id + " has been deleted.", HttpStatus.OK);
    }

    @PutMapping("password")
    public ResponseEntity<UserDto> editPassword(@RequestParam String password, Long id) throws UserNotFoundException {
        User user = userService.editPassword(password, id);
        return new ResponseEntity<>(userMapper.mapToUserDto(user), HttpStatus.CREATED);
    }

    @PutMapping("age")
    public ResponseEntity<UserDto> editAge(@RequestParam String age, Long id) throws UserNotFoundException {
        User user = userService.editAge(Integer.parseInt(age), id);
        return new ResponseEntity<>(userMapper.mapToUserDto(user), HttpStatus.CREATED);
    }

    @PutMapping("email")
    public ResponseEntity<UserDto> editEmail(@RequestParam String email, Long id) throws UserNotFoundException {
        User user = userService.editEmail(email, id);
        return new ResponseEntity<>(userMapper.mapToUserDto(user), HttpStatus.CREATED);
    }

    @PutMapping("name")
    public ResponseEntity<UserDto> editName(@RequestParam String name, Long id) throws UserNotFoundException {
        User user = userService.editName(name, id);
        return new ResponseEntity<>(userMapper.mapToUserDto(user), HttpStatus.CREATED);
    }

    @PutMapping("surname")
    public ResponseEntity<UserDto> editSurname(@RequestParam String surname, Long id) throws UserNotFoundException {
        User user = userService.editSurname(surname, id);
        return new ResponseEntity<>(userMapper.mapToUserDto(user), HttpStatus.CREATED);
    }
}