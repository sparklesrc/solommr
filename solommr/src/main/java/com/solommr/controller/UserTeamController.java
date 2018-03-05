package com.solommr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.solommr.model.ClanDataResponse;
import com.solommr.model.TeamSearchReq;
import com.solommr.service.ClanService;

@Controller
@RequestMapping("/user")
public class UserTeamController {

	@Autowired
	private ClanService clanService;

	@GetMapping("/team")
	public String index(Model model) {
		return "/user/team";
	}

	@RequestMapping(value = "/searchTeam", method = RequestMethod.POST)
	public @ResponseBody ClanDataResponse searchTeam(TeamSearchReq request) {
		ClanDataResponse response = clanService.searchTeam(request);
		return response;
	}
}
