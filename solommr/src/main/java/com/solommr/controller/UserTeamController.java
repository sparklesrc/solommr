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
import com.solommr.model.GenericResponse;
import com.solommr.model.SignUp;
import com.solommr.model.ClanDataResponse.Members;
import com.solommr.model.Reclutar;
import com.solommr.model.Reclutar.ReclutarSearchResult;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.UserInfo;
import com.solommr.model.TeamSearchReq.BuildTeamReq;
import com.solommr.model.TeamSearchReq.DeleteTeamRequest;
import com.solommr.model.TeamSearchReq.RecruitPlayerRequest;
import com.solommr.model.TeamSearchReq.TeamSearchResponse;
import com.solommr.service.ClanService;
import com.solommr.service.GameService;
import com.solommr.service.UserService;
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
	@Autowired
	private UserService userService;

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
	public String buildTeamPost(HttpServletRequest req, BuildTeamReq request, Model model) {
		UserInfo user = this.getCurrentUser(req);
		request.setUserId(user.getUserId());
		String resp = clanService.buildTeam(request);
		if (resp != null && !"error".equals(resp)) {
			UserInfo usuario = userService.getUserByMail(user.getMail());
			req.getSession().setAttribute("SESSION_USUARIO", usuario);
		}
		req.getSession().removeAttribute("SESSION_USUARIO");
		UserInfo usuario = userService.getUserByMail(user.getMail());
		req.getSession().setAttribute("SESSION_USUARIO", usuario);
		return "redirect:/user/team/myTeam?gameId="+request.getGameId();
	}

	@RequestMapping(value = "/team/build", method = RequestMethod.GET)
	public String buildTeamGet(Model model) {
		model.addAttribute("paises", utilService.getCountries());
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
			model.addAttribute("teamId", cDR.getClanId());
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
		UserInfo user = this.getCurrentUser( req);
		if (user == null) {
			return "redirect:/login";
		}
		// Fix Request
		if("0".equals(request.getPais())) {
			request.setPais(null);
		}
		if ("".equals(request.getNickName())) {
			request.setNickName(null);
		}
		if (request.getRol().length == 0) {
			request.setRol(null);
		}
		if ("".equals(request.getEmail())) {
			request.setEmail(null);
		}
		List<ReclutarSearchResult> players = null;
		boolean hasData = false;
		try {
			request.setUserId(user.getUserId());
			players = clanService.searchUsersByCriteria(request);
			if (players != null && !players.isEmpty()) {
				hasData = true;
				model.addAttribute("players", players);
			}
		} catch (Exception e) {
		}
		// ROL 1>Awper 2>Entry Fragger 3>Support 4>Lurker 5>Assault
		// ESTADO 1>Sin Clan 2>Con Clan
		model.addAttribute("hasData", hasData);
		return "user/team/reclutarResult :: reclutarResult";
	}

	@RequestMapping(value = "/team/recruitPlayer", method = RequestMethod.POST)
	public GenericResponse recruitPlayer(RecruitPlayerRequest request, HttpServletRequest req, Model model) {
		UserInfo currentUser = this.getCurrentUser(req);
		if (currentUser == null) {
			return new GenericResponse("error");
		}
		List<List<Integer>> mappedTeams = currentUser.getUserTeams();
		Long clanId = null;
		if (mappedTeams != null) {
			for (List<Integer> e : mappedTeams) {
				if (e != null && e.get(0) == request.getGameId().intValue()) {
					clanId = e.get(1).longValue();
				}
			}
		}

		if (clanId == null) {
			return new GenericResponse("error");
		}

		request.setClanId(clanId);
		return clanService.recruitPlayer(request);
	}

	@RequestMapping(value = "/team/deleteTeam", method = RequestMethod.POST)
	public GenericResponse deleteTeam(DeleteTeamRequest request, HttpServletRequest req, Model model) {
		System.out.println("USER/TEAM " + request.getUserId() + "/" + request.getTeamId());
		UserInfo currentUser = this.getCurrentUser(req);
		if (currentUser == null) {
			return new GenericResponse("error");
		}
		if (request.getIsLeader()) {
			request.setUserId(currentUser.getUserId());
			clanService.deleteTeam(request);
		}
		req.getSession().removeAttribute("SESSION_USUARIO");
		UserInfo usuario = userService.getUserByMail(currentUser.getMail());
		req.getSession().setAttribute("SESSION_USUARIO", usuario);
		return new GenericResponse("ok");
	}
}
