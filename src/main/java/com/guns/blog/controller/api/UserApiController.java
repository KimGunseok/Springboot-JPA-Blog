package com.guns.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.guns.blog.dto.ResponseDto;
import com.guns.blog.model.RoleType;
import com.guns.blog.model.User;
import com.guns.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { //username, password, email
		System.out.println("UserApiController : save 호출");
		// 실제로 DB에 insert를 하고 아래에서 return수행
		
		int reuselt = userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), reuselt);
	}
	
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession session) {
	 * System.out.println("UserApiController : save 호출"); User principal =
	 * userService.로그인(user);
	 * 
	 * if (principal != null) { session.setAttribute("principal", principal); }
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */
}
