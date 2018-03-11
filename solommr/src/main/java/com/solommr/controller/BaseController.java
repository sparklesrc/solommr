package com.solommr.controller;

import javax.servlet.http.HttpServletRequest;

import com.solommr.model.UserInfo;

public class BaseController {

	/**
	 * Return true if the user is already logged in
	 * 
	 * @param request
	 * @return
	 */
	public UserInfo getCurrentUser(HttpServletRequest request) {
		UserInfo normalUser = (UserInfo) request.getSession().getAttribute("SESSION_USUARIO");
		UserInfo steamUser = (UserInfo) request.getSession().getAttribute("STEAM_USUARIO");

		if (normalUser != null) {
			return normalUser;
		} else if (steamUser != null) {
			return steamUser;
		}

		return null;
	}
}
