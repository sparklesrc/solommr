package com.solommr.service;

import com.solommr.model.ClanDataResponse;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.TeamSearchReq.BuildTeamReq;

public interface ClanService {

	ClanDataResponse searchTeam(TeamSearchReq request);

	void buildTeam(BuildTeamReq request);
}
