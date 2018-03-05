package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solommr.model.ClanDataResponse;
import com.solommr.model.UserInfo;
import com.solommr.service.ClanService;

@Controller
public class UserController {

	@Autowired
	private ClanService clanService;

	@GetMapping("/user")
	public String index(HttpServletRequest request, Model model) {
		UserInfo usuario = (UserInfo) request.getSession().getAttribute("SESSION_USUARIO");
		if (usuario == null) {
			return "redirect:/login";
		}
		return "/user/user";
	}

	@GetMapping("/user/home")
	public String userHome(HttpServletRequest request, Model model) {
		UserInfo usuario = (UserInfo) request.getSession().getAttribute("SESSION_USUARIO");
		if (usuario == null) {
			return "redirect:/login";
		}
		return "/user/user";
	}
}
