package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.solommr.model.UserInfo;

@Controller
public class DefaultController extends BaseController {

	@GetMapping("/")
	public String home1() {
		return "/home";
	}

	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		if (this.getCurrentUser(request) == null) {
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
