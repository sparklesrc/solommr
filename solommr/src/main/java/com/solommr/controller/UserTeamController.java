package com.solommr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.solommr.model.ClanDataResponse;
import com.solommr.model.ClanDataResponse.Members;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.TeamSearchReq.TeamSearchResponse;
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
	public String searchTeam(TeamSearchReq request, Model model) {
		ClanDataResponse response = clanService.searchTeam(request);
		model.addAttribute("hasData", response == null ? false : true);
		model.addAttribute("teams", prepareTeamSearchResult(response));
//		return prepareTeamSearchResult(response);
		return "user/teamSearchResult :: teamSearchResult";
	}

	@RequestMapping(value = "/buildTeam", method = RequestMethod.POST)
	public @ResponseBody ClanDataResponse buildTeamPost() {
		return null;
	}

	@RequestMapping(value = "/buildTeam", method = RequestMethod.GET)
	public String buildTeamGet(Model model) {
		model.addAttribute("teams", null);
		return "user/buildTeam :: buildTeam";
	}

	private List<TeamSearchResponse> prepareTeamSearchResult(ClanDataResponse response) {
		List<TeamSearchResponse> ltsr = new ArrayList();
		TeamSearchResponse tsr = new TeamSearchResponse();
		if (response != null) {
			tsr.setId(response.getClanId().toString());
			tsr.setName(response.getClanName());
			response.getClanName();
			for (Members member : response.getMembers()) {
				if ("Team Leader".equals(member.getType())) {
					tsr.setLeader(member.getName());
					break;
				}
			}
		}
		ltsr.add(tsr);
		return ltsr;
	}
}
