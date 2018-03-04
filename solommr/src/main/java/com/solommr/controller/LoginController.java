package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.solommr.model.UserInfo;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		UserInfo usuario = (UserInfo) request.getSession().getAttribute("SESSION_USUARIO");
		if (usuario != null) {
			return "redirect:/user";
		}
		return "/login";
	}
}
