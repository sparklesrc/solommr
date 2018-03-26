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
import com.solommr.model.SignUp;
import com.solommr.model.ClanDataResponse.Members;
import com.solommr.model.Reclutar;
import com.solommr.model.Reclutar.ReclutarSearchResult;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.UserInfo;
import com.solommr.model.TeamSearchReq.TeamSearchResponse;
import com.solommr.service.ClanService;
import com.solommr.service.GameService;
import com.solommr.service.UtilService;

@Controller
@RequestMapping("/user")
public class UserTeamController extends BaseController{

	@Autowired
	private ClanService clanService;
	@Autowired
	private GameService gameService;
	@Autowired
	private UtilService utilService;

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
		if (user == null || gameId == null) {
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
		model.addAttribute("paises", utilService.getCountries());
		return "user/team/myTeam :: myTeam";
	}

	@RequestMapping(value = "/team/reclutarSearch", method = RequestMethod.POST)
	public String reclutarSearch(HttpServletRequest req, Reclutar request, Model model) {
		request.getEdad();
		// ROL 1>Awper 2>Entry Fragger 3>Support 4>Lurker
		List<ReclutarSearchResult> players = new ArrayList();
		ReclutarSearchResult player = new ReclutarSearchResult();
		player.setEdad(21);
		player.setId(5);
		player.setMail("myMail@gmail.com");
		player.setNickName("myNickName");
		player.setPais("PE");
		player.setRoles("Awp, Support");

		ReclutarSearchResult player2 = new ReclutarSearchResult();
		player2.setEdad(21);
		player2.setId(5);
		player2.setMail("myMail@gmail.com");
		player2.setNickName("myNickName");
		player2.setPais("PE");
		player2.setRoles("Awp, Support");
		
		players.add(player);
		players.add(player2);
		
		model.addAttribute("players", players);
		model.addAttribute("hasData", true);
		return "user/team/reclutarResult :: reclutarResult";
	}
}
