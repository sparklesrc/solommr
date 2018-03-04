package com.solommr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.solommr.adapter.UserAdapter;
import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.UserInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAdapter userAdapter;

	@Override
	public UserInfo getUserByMail(String mail) {
		System.out.println("En Service");
		return userAdapter.getUserInfoByMail(mail);
	}

	@Override
	public SteamCSGOProfile getSteamProfile() {
		SteamCSGOProfile csgoProfile = userAdapter.getSteamProfile();
		if (csgoProfile != null) {

		}

		return csgoProfile;
	}

}
