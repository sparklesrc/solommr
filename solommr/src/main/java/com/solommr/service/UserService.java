package com.solommr.service;

import com.solommr.model.GenericResponse;
import com.solommr.model.GenericResponse.GenericResponse2;
import com.solommr.model.GenericResponse.InvitationsToTeamResponse;
import com.solommr.model.SignUp;
import com.solommr.model.SignUp.BasicRequest;
import com.solommr.model.SignUp.Pin;
import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.SteamPlayerSummarie;
import com.solommr.model.TeamSearchReq.RecruitPlayerRequest;
import com.solommr.model.UserInfo;
import com.solommr.model.UserInfo.InvitationsTeamsRequest;
import com.solommr.model.UserInfo.UserGameProfile;

public interface UserService {

	UserInfo getUserByMail(String mail);

	SteamCSGOProfile getSteamProfile(String steamId);

	UserInfo syncSteamUser(Long userId, String steamId);

	SteamPlayerSummarie getSteamPlayerSummarie(String steamId);

	GenericResponse signUp(SignUp request);

	UserInfo confirmPin(Pin request);

	GenericResponse reSendCode(BasicRequest request);

	GenericResponse verifyCode(Pin request);

	UserGameProfile getUserGameProfile(Long userId, Integer gameId);

	GenericResponse updateUserGameProfile(UserGameProfile request);

	GenericResponse2<InvitationsToTeamResponse> getInvitationTeams(InvitationsTeamsRequest request);

}
