package com.guns.blog.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"","/"})
	public String index() {
		// /WEB-INF/views/index.jsp
		return "index";
	}
}
