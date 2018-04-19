package com.solommr.service;

import java.util.List;

import com.solommr.model.ClanDataResponse;
import com.solommr.model.Reclutar;
import com.solommr.model.Reclutar.ReclutarSearchResult;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.TeamSearchReq.BuildTeamReq;

public interface ClanService {

	ClanDataResponse searchTeam(TeamSearchReq request);

	String buildTeam(BuildTeamReq request);

	List<ReclutarSearchResult> searchUsersByCriteria(Reclutar request);
}
