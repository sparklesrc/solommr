package com.solommr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.solommr.model.ClanDataResponse;
import com.solommr.service.ClanService;

@Controller
public class TeamController {

	@Autowired
	private ClanService clanService;

	@GetMapping("/team")
	public String index(Model model) {
		System.out.println("EN CONTROLLER");
		ClanDataResponse clanData = clanService.getClanData();
		model.addAttribute("clanData", clanData);
		return "/team";
	}
}
