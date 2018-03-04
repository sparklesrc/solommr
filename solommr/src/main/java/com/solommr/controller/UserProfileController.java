package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solommr.model.UserInfo;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController {

	@GetMapping
	public String index(HttpServletRequest request, Model model) {
		UserInfo usuario = (UserInfo) request.getSession().getAttribute("SESSION_USUARIO");
		boolean isNotSincronized = false;
		if (usuario == null) {
			return "redirect:/login";
		}
		if (usuario.getSteamId() != null) {
			isNotSincronized = true;
		}
		model.addAttribute("isNotSincronized", isNotSincronized);
		return "/user/profile";
	}

	public String sincronizeAccount(Model model) {
		return "/user/profile";
	}
}
