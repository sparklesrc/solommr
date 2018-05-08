package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.solommr.model.UserInfo;
import com.solommr.service.ClanService;

@Controller
public class UserController extends BaseController {

	@Autowired
	private ClanService clanService;

	@GetMapping("/user")
	public String index(HttpServletRequest request, Model model) {
		UserInfo userInfo = this.getCurrentUser(request);
		if (userInfo == null) {
			return "redirect:/login";
		}
		if (!userInfo.isActive()) {
			model.addAttribute("msg", "user created");
			model.addAttribute("email", userInfo.getMail());
			return "/confirmMail";
		}
		return "/user/user";
	}

	@GetMapping("/user/home")
	public String userHome(HttpServletRequest request, Model model) {
		if (this.getCurrentUser(request) == null) {
			return "redirect:/login";
		}
		return "/user/user";
	}
}
