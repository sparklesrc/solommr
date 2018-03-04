package com.solommr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solommr.model.ClanDataResponse;

@Controller
@RequestMapping("/user")
public class UserMMRController {

	@GetMapping("/mmr")
	public String index(Model model) {
		return "/user/mmr";
	}
}
