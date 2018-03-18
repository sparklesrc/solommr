package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.solommr.config.SteamOpenID;
import com.solommr.model.UserInfo;
import com.solommr.service.UserService;

@Controller
public class LoginSteamController extends BaseController{

	private final SteamOpenID openid = new SteamOpenID();

	@Autowired
	private UserService userService;

	@Value("${solommr.url.base}")
	private String solommr;

	private String getFullUrl(HttpServletRequest request, String path) {
//		StringBuilder builder = new StringBuilder("localhost:8080/solommr");
		StringBuilder builder = new StringBuilder(solommr + "/solommr");
		builder.insert(0, "http://");
		builder.append(path);
		return builder.toString();
	}

	@GetMapping("/trade")
	public ModelAndView trade(HttpServletRequest request, HttpServletResponse response) {
		String redirectTo = openid.login(getFullUrl(request, "/auth"));
		return new ModelAndView("redirect:" + redirectTo);
	}

	@GetMapping("/auth")
	public ModelAndView auth(HttpServletRequest request, HttpServletResponse response) {
		String steamId = openid.verify(request.getRequestURL().toString(), request.getParameterMap());
		String fullUrl = getFullUrl(request, "/user");
		if (steamId == null) {
			return new ModelAndView("redirect:" + fullUrl);
		}
		UserInfo userInfo = this.getCurrentUser(request);
		// Update UserInfo with Steam User
		UserInfo userUpdated = userService.syncSteamUser(userInfo.getUserId(), steamId);
		request.getSession().setAttribute("SESSION_USUARIO", userUpdated);

		return new ModelAndView("redirect:" + fullUrl);
	}
}
