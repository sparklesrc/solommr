package com.solommr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserSolicitudeController {

	@GetMapping("/solicitude")
	public String index(Model model) {
		return "/user/solicitude";
	}
}
