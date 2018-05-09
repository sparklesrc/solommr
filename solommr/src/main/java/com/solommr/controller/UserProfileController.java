package com.solommr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solommr.model.ClanDataResponse;
import com.solommr.model.GenericResponse;
import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.SteamCSGOProfile.Stats;
import com.solommr.model.UserInfo.UserGameProfile;
import com.solommr.model.SteamPlayerSummarie;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.UserInfo;
import com.solommr.service.GameService;
import com.solommr.service.UserService;
import com.solommr.service.UtilService;
import com.solommr.util.EnumPais;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private GameService gameService;
	@Autowired
	private UtilService utilService;

	@GetMapping
	public String getSteamProfile(HttpServletRequest request, Model model) {
		UserInfo currentUser = this.getCurrentUser(request);
		if (currentUser == null) {
			return "redirect:/login";
		}

		model.addAttribute("games", gameService.getActiveGames());
		return "/user/profile";
	}

	@GetMapping("/editProfile")
	public String getEditProfile(HttpServletRequest request, Model model) {
		UserInfo currentUser = this.getCurrentUser(request);
		if (currentUser == null) {
			return "redirect:/login";
		}
		model.addAttribute("mail", currentUser.getMail());
		model.addAttribute("pais", utilService.getPaisByCode(currentUser.getPais()));
		model.addAttribute("edad", currentUser.getEdad());
		String steamId = "Cuenta no Sincronizada.";
		if (currentUser.getSteamId() != null && !"".equals(currentUser.getSteamId())) {
			steamId = currentUser.getSteamId();
		}
		model.addAttribute("steamId", steamId);
		return "/user/profile/editProfile :: editProfile";
	}

	@GetMapping("/editMyGameProfile")
	public String getEditMyGameProfile(HttpServletRequest request, Model model) {
		String gameId = (String) request.getParameter("gameId");
		UserInfo currentUser = this.getCurrentUser(request);
		if (currentUser == null) {
			return "redirect:/login";
		}
		UserGameProfile userGameProfile = userService.getUserGameProfile(currentUser.getUserId(), new Integer(gameId));
		if (userGameProfile != null) {
			model.addAttribute("nickName", userGameProfile.getNickname());
			model.addAttribute("celular", userGameProfile.getCelular());
			model.addAttribute("description", userGameProfile.getDescription());
			model.addAllAttributes(getRolesForProfile(userGameProfile.getRoles()));
		}
		return "/user/profile/myGameProfile :: myGameProfile";
	}

	private Map getRolesForProfile(String... roles) {
		Map<String, Object> mapRoles = new HashMap<String, Object>();
		boolean isAwper = false;
		boolean isEntryFragger = false;
		boolean isSupport = false;
		boolean isLurker = false;
		boolean isAssault = false;
		if (roles != null) {
			for (String rol : roles) {
				Integer code = Integer.parseInt("" + rol.charAt(0));
				switch (code) {
				case 1:
					isAwper = true;
					break;
				case 2:
					isEntryFragger = true;
					break;
				case 3:
					isSupport = true;
					break;
				case 4:
					isLurker = true;
					break;
				case 5:
					isAssault = true;
					break;

				default:
					break;
				}
			}
		}
		mapRoles.put("isAwper", isAwper);
		mapRoles.put("isEntryFragger", isEntryFragger);
		mapRoles.put("isSupport", isSupport);
		mapRoles.put("isLurker", isLurker);
		mapRoles.put("isAssault", isAssault);
		return mapRoles;
	}

	@RequestMapping(value = "/updateGameProfile", method = RequestMethod.POST)
	public String editMyGameProfile(HttpServletRequest req, UserGameProfile request, Model model) {
		// update Game Profile
		UserInfo currentUser = this.getCurrentUser(req);
		String status = "error";
		if (currentUser == null) {
			return "redirect:/login";
		}
		request.setUserId(currentUser.getUserId());
		GenericResponse gR = userService.updateUserGameProfile(request);
		if (gR != null && !"".equals(gR.getMsg())) {
			status = gR.getMsg();
		}
		model.addAttribute("status", status);
		return "/user/profile/myGameProfile :: myGameProfile";
	}

	@GetMapping("/view")
	public String viewProfile(HttpServletRequest request, Model model) {
		String gameId = (String) request.getParameter("gameId");
		UserInfo currentUser = this.getCurrentUser(request);
		if (currentUser == null) {
			return "redirect:/login";
		}
		model.addAttribute("isUserSyncWithSteam", currentUser.isUserSyncWithSteam());
		model.addAttribute("gameId", gameId);

		getPlayerSummarie(model, currentUser.getSteamId());
		
		// CSGO
		if ("1".equals(gameId) && currentUser.isUserSyncWithSteam()) {
			SteamCSGOProfile steamCSGOProfile = userService.getSteamProfile(currentUser.getSteamId());
			Map<String, Long> stats = converListToMap(steamCSGOProfile.getPlayerstats().getStats());
			model.addAttribute("total_kills", stats.get("total_kills"));
		}

		return "/user/profile/myStats :: myStats";
	}

	private Map<String, Long> converListToMap(List<Stats> stats) {
		Map<String, Long> resultsMap = new HashMap<String, Long>();
		for (Stats o : stats) {
			resultsMap.put((String) o.getName(), (Long) o.getValue());
		}
		return resultsMap;
	}

	private void getPlayerSummarie(Model model, String steamId) {
		// PlayerSummarie
		SteamPlayerSummarie steamPlayerSummarie = userService.getSteamPlayerSummarie(steamId);
		if (steamPlayerSummarie != null) {
			model.addAttribute("imgProfile", steamPlayerSummarie.getResponse().getPlayers().get(0).getAvatarfull());
			model.addAttribute("nickName", steamPlayerSummarie.getResponse().getPlayers().get(0).getPersonaname());
			model.addAttribute("realName", steamPlayerSummarie.getResponse().getPlayers().get(0).getRealname());
			model.addAttribute("profileUrl", steamPlayerSummarie.getResponse().getPlayers().get(0).getProfileurl());
		}
	}
}
