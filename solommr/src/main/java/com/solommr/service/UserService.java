package com.solommr.service;

import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.SteamPlayerSummarie;
import com.solommr.model.UserInfo;

public interface UserService {

	UserInfo getUserByMail(String mail);

	SteamCSGOProfile getSteamProfile(String steamId);

	UserInfo syncSteamUser(Long userId, String steamId);

	SteamPlayerSummarie getSteamPlayerSummarie(String steamId);
}
