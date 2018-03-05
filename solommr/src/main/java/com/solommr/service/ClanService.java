package com.solommr.service;

import com.solommr.model.ClanDataResponse;
import com.solommr.model.TeamSearchReq;

public interface ClanService {

	ClanDataResponse searchTeam(TeamSearchReq request);
}
