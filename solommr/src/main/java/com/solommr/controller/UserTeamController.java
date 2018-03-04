package com.solommr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solommr.model.ClanDataResponse;
import com.solommr.service.ClanService;

@Controller
@RequestMapping("/user")
public class UserTeamController {

	@Autowired
	private ClanService clanService;

	@GetMapping("/team")
	public String index(Model model) {
		System.out.println("EN CONTROLLER");
//		ClanDataResponse clanData = clanService.getClanData();
//		model.addAttribute("clanData", clanData);
		return "/user/team";
	}
}
