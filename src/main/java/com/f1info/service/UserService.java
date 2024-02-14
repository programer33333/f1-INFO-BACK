package com.f1info.service;

import com.f1info.domain.history.History;
import com.f1info.domain.user.User;
import com.f1info.exceptions.UserNotFoundException;
import com.f1info.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(final Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User saveUser(final User user) {
        List<History> history = new ArrayList<>();
        user.setHistory(history);
        return userRepository.save(user);
    }

    public void deleteUser(final Long id) throws UserNotFoundException{
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(user.getId());
    }

    public User editPassword(final String password, final Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User editAge(final int age, final Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setAge(age);
        return userRepository.save(user);
    }

    public User editEmail(final String email, final Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setEmail(email);
        return userRepository.save(user);
    }
    public User editName(final String name, final Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setName(name);
        return userRepository.save(user);
    }

    public User editSurname(final String surname, final Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setSurname(surname);
        return userRepository.save(user);
    }

    public boolean isLoginAlreadyExist(String login) {
        return userRepository.existsByLogin(login);
    }
}