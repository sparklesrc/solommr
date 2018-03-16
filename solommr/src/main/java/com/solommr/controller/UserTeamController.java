package com.solommr.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.solommr.model.ClanDataResponse;
import com.solommr.model.ClanDataResponse.Members;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.UserInfo;
import com.solommr.model.TeamSearchReq.TeamSearchResponse;
import com.solommr.service.ClanService;
import com.solommr.service.GameService;

@Controller
@RequestMapping("/user")
public class UserTeamController extends BaseController{

	@Autowired
	private ClanService clanService;
	@Autowired
	private GameService gameService;

	@GetMapping("/team")
	public String index(Model model) {
		model.addAttribute("games", gameService.getActiveGames());
		return "/user/team";
	}

	@RequestMapping(value = "/team/search", method = RequestMethod.POST)
	public String searchTeam(HttpServletRequest req, TeamSearchReq request, Model model) {
		ClanDataResponse response = clanService.searchTeam(request);
		model.addAttribute("hasData", response == null ? false : true);
		model.addAttribute("teams", prepareTeamSearchResult(response));
		return "/user/team/teamSearchResult :: teamSearchResult";
	}

	@RequestMapping(value = "/team/build", method = RequestMethod.POST)
	public @ResponseBody ClanDataResponse buildTeamPost() {
		return null;
	}

	@RequestMapping(value = "/team/build", method = RequestMethod.GET)
	public String buildTeamGet(Model model) {
		model.addAttribute("teams", null);
		return "user/team/build :: buildTeam";
	}

	@RequestMapping(value = "/team/profile", method = RequestMethod.POST)
	public String getTeamProfile(TeamSearchReq request, Model model) {
		ClanDataResponse cDR = clanService.searchTeam(request);
		model.addAttribute("teamName", cDR.getClanName());
		model.addAttribute("members", cDR.getMembers());
		return "user/team/profile :: profile";
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

	@GetMapping("/team/myTeam")
	public String myTeam(HttpServletRequest request, Model model) {
		String gameId = (String) request.getParameter("gameId");
		UserInfo user = this.getCurrentUser(request);
		boolean userHasTeam = false;
		if (user == null && gameId == null) {
			return "redirect:/login";
		}
		List<List<Integer>> mappedTeams = user.getUserTeams();
		String clanId = null;
		if(mappedTeams!=null) {
			for(List<Integer> e : mappedTeams) {
				if(e!=null && e.get(0)==Integer.parseInt(gameId)) {
					clanId = e.get(1).toString();
					userHasTeam = true;
					break;
				}
			}
		}

		if (userHasTeam && clanId != null) {
			TeamSearchReq req = new TeamSearchReq();
			req.setCriteria(clanId);
			req.setGameId(gameId);
			boolean isLeader = false;
			ClanDataResponse cDR = clanService.searchTeam(req);
			model.addAttribute("teamName", cDR.getClanName());
			model.addAttribute("members", cDR.getMembers());
			for(Members member : cDR.getMembers()) {
				if(member.getUserId().equals(user.getUserId())) {
					isLeader = "Team Leader".equals(member.getType());
					break;
				}
			}
			model.addAttribute("isLeader", isLeader);
		}

		model.addAttribute("userHasTeam", userHasTeam);
		return "user/team/myTeam :: myTeam";
	}
}
