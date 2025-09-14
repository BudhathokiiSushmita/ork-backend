package com.sushmita.ork;

import com.sushmita.ork.entity.OrkUser;
import com.sushmita.ork.service.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrkApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		OrkUser user = OrkUser.builder()
				.username("john")
				.build();

		OrkUser savedUser = userRepository.save(user);
		System.out.println("User ID: " + savedUser.getId());  // Now this prints a real ID

	}

}
