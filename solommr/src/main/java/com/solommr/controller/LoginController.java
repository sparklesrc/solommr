package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends BaseController {

	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		if (this.getCurrentUser(request) != null) {
			return "redirect:/user";
		}
		return "/login";
	}
}
