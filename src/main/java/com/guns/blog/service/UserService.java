package com.guns.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guns.blog.model.RoleType;
import com.guns.blog.model.User;
import com.guns.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 = IoC를 해줌
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public int 회원가입(User user) {
		try {
			//password 암호화(해쉬)
			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole(RoleType.USER);
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("UserService:회원가입()" + e.getMessage());
		}
		return -1;
	}

	@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 보장)
	public User 로그인(User user) {
//		return userRepository.login(user.getUsername(), user.getPassword()); //native query 방식
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()); //JPA naming 전략
	}
}
