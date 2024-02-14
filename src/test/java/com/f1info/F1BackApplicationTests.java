package com.f1info;

import com.f1info.domain.user.User;
import com.f1info.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class F1BackApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void findUserTest() {
		User user = new User();
		userRepository.save(user);
		Long id = user.getId();
		Optional<User> findUser = userRepository.findById(id);
		assertTrue(findUser.isPresent());

		userRepository.deleteById(id);
	}

}
