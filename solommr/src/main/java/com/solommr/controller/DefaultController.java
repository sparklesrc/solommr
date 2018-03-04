package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.solommr.model.UserInfo;

@Controller
public class DefaultController {

	@GetMapping("/")
	public String home1() {
		return "/home";
	}

	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		UserInfo usuario = (UserInfo) request.getSession().getAttribute("SESSION_USUARIO");
		if (usuario != null) {
			return "redirect:/user";
		}
		return "/home";
	}

	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}

	@GetMapping("/about")
	public String about() {
		return "/about";
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}
}
