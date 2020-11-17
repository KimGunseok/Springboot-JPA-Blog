package com.guns.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG="HttpControllerTest :";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("guns").password(1234).email("test@test.com").build();
		System.out.println(TAG+"getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter : " + m.getId());
		
		return "lombok test 완료";
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) { //http://localhost:8080/http/get?id=1&username=guns&password=aa&email=tt@cst.com
		
		Member m1 = new Member(1, "guns", 1234, "test@test.com");
		return "get 요청 ID : " + m.getId() + " , UserName : " + m.getUsername() + " , Password : " + m.getPassword() + " , Email : " + m.getEmail();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // MessageConverter (스프링부트)
		return "post 요청 ID : " + m.getId() + " , UserName : " + m.getUsername() + " , Password : " + m.getPassword() + " , Email : " + m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
