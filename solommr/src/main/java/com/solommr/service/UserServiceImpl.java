package com.solommr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.solommr.adapter.UserAdapter;
import com.solommr.model.SignUp;
import com.solommr.model.SignUp.Pin;
import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.SteamPlayerSummarie;
import com.solommr.model.UserInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAdapter userAdapter;

	@Override
	public UserInfo getUserByMail(String mail) {
		return userAdapter.getUserInfoByMail(mail);
	}

	@Override
	public SteamCSGOProfile getSteamProfile(String steamId) {
		SteamCSGOProfile csgoProfile = userAdapter.getSteamProfile(steamId);
		return csgoProfile;
	}

	@Override
	public UserInfo syncSteamUser(Long userId, String steamId) {
		return userAdapter.syncSteamUser(userId, steamId);
	}

	@Override
	public SteamPlayerSummarie getSteamPlayerSummarie(String steamId) {
		return userAdapter.getSteamPlayerSummarie(steamId);
	}

	@Override
	public void signUp(SignUp request) {
	}

	@Override
	public UserInfo confirmPin(Pin request) {
		return null;
	}

}
