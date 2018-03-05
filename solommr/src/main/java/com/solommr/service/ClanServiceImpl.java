package com.solommr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solommr.adapter.ClanAdapter;
import com.solommr.model.ClanDataResponse;
import com.solommr.model.TeamSearchReq;

@Service
public class ClanServiceImpl implements ClanService {

	@Autowired
	private ClanAdapter clanAdapter;

	@Override
	public ClanDataResponse searchTeam(TeamSearchReq request) {
		return clanAdapter.getClanData(new Long(request.getGameId()), request.getCriteria());
	}

}
