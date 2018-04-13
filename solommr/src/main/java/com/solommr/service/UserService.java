package com.solommr.service;

import com.solommr.model.GenericResponse;
import com.solommr.model.SignUp;
import com.solommr.model.SignUp.BasicRequest;
import com.solommr.model.SignUp.Pin;
import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.SteamPlayerSummarie;
import com.solommr.model.UserInfo;

public interface UserService {

	UserInfo getUserByMail(String mail);

	SteamCSGOProfile getSteamProfile(String steamId);

	UserInfo syncSteamUser(Long userId, String steamId);

	SteamPlayerSummarie getSteamPlayerSummarie(String steamId);

	GenericResponse signUp(SignUp request);

	UserInfo confirmPin(Pin request);

	GenericResponse reSendCode(BasicRequest request);

	GenericResponse verifyCode(Pin request);
}
