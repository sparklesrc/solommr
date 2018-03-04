package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.SteamCSGOProfile.Stats;
import com.solommr.model.UserInfo;
import com.solommr.service.UserService;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String index(HttpServletRequest request, Model model) {
		UserInfo usuario = (UserInfo) request.getSession().getAttribute("SESSION_USUARIO");
		boolean isNotSincronized = false;
		if (usuario == null) {
			return "redirect:/login";
		}
		if (usuario.getSteamId() == null) {
			isNotSincronized = true;
		}
		
		SteamCSGOProfile steamCSGOProfile = userService.getSteamProfile();
		for(Stats stat : steamCSGOProfile.getPlayerstats().getStats()){
			if("total_kills".equals(stat.getName())){
				model.addAttribute("total_kills", stat.getValue());
			}
		}
		
		model.addAttribute("isNotSincronized", isNotSincronized);
		return "/user/profile";
	}

	public String sincronizeAccount(Model model) {
		return "/user/profile";
	}
}
