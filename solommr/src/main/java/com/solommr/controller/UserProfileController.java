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

import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.SteamCSGOProfile.Stats;
import com.solommr.model.UserInfo;
import com.solommr.service.GameService;
import com.solommr.service.UserService;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private GameService gameService;

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
		return "/user/profile/editProfile :: editProfile";
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

		// CSGO
		if ("1".equals(gameId)) {
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
}
