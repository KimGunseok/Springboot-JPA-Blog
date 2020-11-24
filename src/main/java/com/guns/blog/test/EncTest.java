package com.guns.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	@Test
	public void 해쉬_암호화() {
		System.out.println(new BCryptPasswordEncoder().encode("1234")); 
	}
}
