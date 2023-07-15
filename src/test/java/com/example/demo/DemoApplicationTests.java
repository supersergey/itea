package com.example.demo;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		assert userRepository != null;
		assert userRepository instanceof UserRepositoryImpl;
	}

}
