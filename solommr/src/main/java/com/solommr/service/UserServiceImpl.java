package com.solommr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.solommr.adapter.UserAdapter;
import com.solommr.model.GenericResponse;
import com.solommr.model.GenericResponse.SignUpGameProfile;
import com.solommr.model.GenericResponse.SignUpRequest;
import com.solommr.model.SignUp;
import com.solommr.model.SignUp.BasicRequest;
import com.solommr.model.SignUp.Pin;
import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.SteamPlayerSummarie;
import com.solommr.model.TeamSearchReq.RecruitPlayerRequest;
import com.solommr.model.UserInfo;
import com.solommr.model.UserInfo.UserGameProfile;

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
	public GenericResponse signUp(SignUp request) {
		return userAdapter.signUp(getSignUpRequest(request));
	}

	@Override
	public UserInfo confirmPin(Pin request) {
		return null;
	}

	private SignUpRequest getSignUpRequest(SignUp request) {
		if (request != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			SignUpRequest signUpRequest = new SignUpRequest();
			signUpRequest.setEdad(request.getEdad());
			signUpRequest.setEmail(request.getEmail());
			signUpRequest.setPais(request.getPais());
			signUpRequest.setPassword(passwordEncoder.encode(request.getPassword()));
			SignUpGameProfile gameProfile = new SignUpGameProfile();
			gameProfile.setGameId(request.getGame());
			gameProfile.setNickname(request.getNickName());
			return signUpRequest;
		}
		return null;
	}

	@Override
	public GenericResponse reSendCode(BasicRequest request) {
		return userAdapter.reSendCode(request);
	}

	@Override
	public GenericResponse verifyCode(Pin request) {
		return userAdapter.verifyCode(request);
	}

	@Override
	public UserGameProfile getUserGameProfile(Long userId, Integer gameId) {
		return userAdapter.getUserGameProfile(userId, gameId.longValue());
	}

	@Override
	public GenericResponse updateUserGameProfile(UserGameProfile request) {
		return userAdapter.updateGameProfile(request);
	}
}
