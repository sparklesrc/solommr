package com.solommr.service;

import java.util.List;

import com.solommr.model.ClanDataResponse;
import com.solommr.model.GenericResponse;
import com.solommr.model.Reclutar;
import com.solommr.model.Reclutar.ReclutarSearchResult;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.TeamSearchReq.BuildTeamReq;
import com.solommr.model.TeamSearchReq.DeleteTeamRequest;
import com.solommr.model.TeamSearchReq.RecruitPlayerRequest;

public interface ClanService {

	ClanDataResponse searchTeam(TeamSearchReq request);

	String buildTeam(BuildTeamReq request);

	List<ReclutarSearchResult> searchUsersByCriteria(Reclutar request);

	GenericResponse recruitPlayer(RecruitPlayerRequest request);

	GenericResponse deleteTeam(DeleteTeamRequest request);

	GenericResponse userHasTeamByGameId(RecruitPlayerRequest request);
}
