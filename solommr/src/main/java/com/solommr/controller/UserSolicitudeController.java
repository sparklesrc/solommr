package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solommr.model.UserInfo;
import com.solommr.model.UserInfo.InvitationsTeamsRequest;
import com.solommr.model.GenericResponse.GenericResponse2;
import com.solommr.model.GenericResponse.InvitationsToTeamResponse;
import com.solommr.service.UserService;

@Controller
@RequestMapping("/user")
public class UserSolicitudeController extends BaseController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/solicitude")
	public String index(HttpServletRequest request, Model model) {
		UserInfo userInfo = this.getCurrentUser(request);
		if (userInfo == null) {
			return "redirect:/login";
		}
		InvitationsTeamsRequest obj = new InvitationsTeamsRequest();
		obj.setGameId(1L);
		obj.setSolicitudeType(1L);
		obj.setUserId(2L);
		GenericResponse2<InvitationsToTeamResponse> lista = userService.getInvitationTeams(obj);
		System.out.println("Lista " + lista);
		return "/user/solicitude";
	}
}
