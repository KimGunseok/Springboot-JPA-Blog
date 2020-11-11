package com.guns.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링이 com.guns.blog 패키지 이하를 스캔
// 특정 어노테이션이 붙어있는 클래스파일을 new해서 IOC 스프링 컨테이너에 관리
@RestController
public class BlogControllerTest {
	
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
