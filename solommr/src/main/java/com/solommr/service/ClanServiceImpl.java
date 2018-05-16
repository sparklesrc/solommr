package com.solommr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.solommr.adapter.ClanAdapter;
import com.solommr.model.ClanDataResponse;
import com.solommr.model.GenericResponse;
import com.solommr.model.Reclutar;
import com.solommr.model.Reclutar.ReclutarSearchResult;
import com.solommr.model.TeamSearchReq;
import com.solommr.model.TeamSearchReq.BuildTeamReq;
import com.solommr.model.TeamSearchReq.RecruitPlayerRequest;

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
			return clanAdapter.buildTeam(request);
		} catch (Exception e) {
			System.out.println("Error al crear equipo.");
		}
		return "error";
	}

	@Override
	public List<ReclutarSearchResult> searchUsersByCriteria(Reclutar request) {
		ReclutarSearchResult[] result = clanAdapter.searchUsersByCriteria(request);
		List<ReclutarSearchResult> rst = new ArrayList();
		for (ReclutarSearchResult e : result) {
			String roles = e.getRoles();
			e.setRoles(getRoles(roles));
			rst.add(e);
		}
		return rst;
	}

	private String getRoles(String roles) {
		String str = "";
		if (roles == null || "".equals(roles)) {
			return "Sin Definir";
		}
		String rol[] = roles.split(",");
		for (String r : rol) {
			if (r.contains("1"))
				str += "|Awper|";
			if (r.contains("2"))
				str += "|Entry Fragger|";
			if (r.contains("3"))
				str += "|Support|";
			if (r.contains("4"))
				str += "|Lurker|";
			if (r.contains("5"))
				str += "|Assault|";
		}
		return str;
	}

	@Override
	public GenericResponse recruitPlayer(RecruitPlayerRequest request) {
		GenericResponse gR = clanAdapter.recruitPlayer(request);
		if (gR == null) {
			new GenericResponse("error");
		}
		return gR;
	}
}
