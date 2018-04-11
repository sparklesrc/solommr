package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.solommr.model.GenericResponse;
import com.solommr.model.SignUp;
import com.solommr.model.SignUp.Pin;
import com.solommr.model.UserInfo;
import com.solommr.service.UserService;
import com.solommr.service.UtilService;

@Controller
public class SignUpController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private UtilService utilService;

	@GetMapping("/signup")
	public String getSignup(HttpServletRequest request, Model model) {
		model.addAttribute("paises", utilService.getCountries());
		if (this.getCurrentUser(request) != null) {
			return "/user/user";
		}
		return "/signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postSignup(HttpServletRequest req, SignUp request, Model model) {
		GenericResponse response = userService.signUp(request);
		String msg = "error";
		if (response != null) {
			msg = response.getMsg();
		}
		model.addAttribute("msg", msg);
		return "/confirmMail";
	}

	@RequestMapping(value = "/validatePin", method = RequestMethod.POST)
	public String validatePin(HttpServletRequest req, Pin request, Model model) {
		UserInfo user = userService.confirmPin(request);
		if (user == null) {
			return "/home";
		}
		req.getSession().setAttribute("SESSION_USUARIO", user);
		return "/user/user";
	}
}
