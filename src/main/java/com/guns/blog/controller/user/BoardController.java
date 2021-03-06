package com.guns.blog.controller.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.guns.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) { // 컨트롤러에서 세션을 가져오는 법
		// /WEB-INF/views/index.jsp
		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		return "index";
	}
}
