package com.solommr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.solommr.adapter.ClanAdapter;
import com.solommr.model.ClanDataResponse;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.TeamSearchReq.BuildTeamReq;

@Service
public class ClanServiceImpl implements ClanService {

	@Autowired
	private ClanAdapter clanAdapter;

	@Override
	public ClanDataResponse searchTeam(TeamSearchReq request) {
		return clanAdapter.getClanData(new Long(request.getGameId()), request.getCriteria());
	}

	@Override
	public String buildTeam(BuildTeamReq request) {
		try {
//			String response = clanAdapter.buildTeam(request);
//			if(response == null)
				return "error";
		} catch (Exception e) {
			System.out.println("Error al crear equipo.");
			return "error";
		}
	}

}
