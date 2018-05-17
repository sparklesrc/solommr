package com.solommr.adapter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solommr.model.ClanDataResponse;
import com.solommr.model.Game;
import com.solommr.model.GenericResponse;
import com.solommr.model.Reclutar;
import com.solommr.model.UserInfo;
import com.solommr.model.Reclutar.ReclutarSearchResult;
import com.solommr.model.TeamSearchReq.BuildTeamReq;
import com.solommr.model.TeamSearchReq.DeleteTeamRequest;
import com.solommr.model.TeamSearchReq.RecruitPlayerRequest;
import com.solommr.model.UserInfo.UserGameProfile;

@Component
public class ClanAdapter extends BaseAdapter {

	@Autowired
	private RestTemplate restTemplate;

	// @Value("${projectrc.url.team.search}")
	private String teamSearch;

	@Value("${projectrc.url.team.build}")
	private String buildTeam;

	@Value("${projectrc.url.team.searchUsersByCriteria}")
	private String searchUsersByCriteria;

	@Value("${projectrc.url.team.recruitPlayer}")
	private String recruitPlayer;

	@Value("${projectrc.url.team.delete}")
	private String deleteTeam;

	public ClanDataResponse getClanData(Long gameId, String criteria) {
		String uri = "http://projectrc-pj-solo-mmr.7e14.starter-us-west-2.openshiftapps.com/projectrc/rest/team/search";

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");

		headers.setAll(map);

		Map req_payload = new HashMap();
		req_payload.put("gameId", gameId);
		req_payload.put("criteria", criteria);

		HttpEntity<?> request = new HttpEntity(req_payload, headers);

		ResponseEntity<?> response = new RestTemplate().postForEntity(uri, request, String.class);

		ObjectMapper mapper = new ObjectMapper();
		ClanDataResponse obj = null;
		try {
			obj = mapper.readValue(response.getBody().toString(), ClanDataResponse.class);
		} catch (Exception e) {
			System.out.println("ERROR EN MAPPING " + criteria);
		}
		return obj;
	}

	public String buildTeam(BuildTeamReq request) {
		Map req_payload = new HashMap();
		req_payload.put("gameId", request.getGameId());
		req_payload.put("userId", request.getUserId());
		req_payload.put("nombre", request.getName());
		req_payload.put("abreviatura", request.getShortName());
		req_payload.put("descripcion", request.getDescription());
		req_payload.put("imgUrl", null);
		req_payload.put("pais", request.getCountry());

		String response = this.doPostCall(req_payload, buildTeam);

		if (response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, String.class);
		} catch (Exception e) {
			System.out.println("Error en Mapping Response - Build Team");
			return null;
		}
	}

	public String deleteTeam(DeleteTeamRequest request) {
		Map req_payload = new HashMap();
		req_payload.put("clanId", request.getTeamId());
		req_payload.put("userId", request.getUserId());

		String response = this.doPostCall(req_payload, deleteTeam);

		if (response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, String.class);
		} catch (Exception e) {
			System.out.println("Error en Mapping Response - Delete Team");
			return null;
		}
	}
	public ReclutarSearchResult[] searchUsersByCriteria(Reclutar request) {
		Map req_payload = new HashMap();
		req_payload.put("gameId", request.getGameId());
		req_payload.put("nickName", request.getNickName());
		req_payload.put("email", request.getEmail());
		req_payload.put("edad", request.getEdad());
		req_payload.put("estado", request.getEstado());
		req_payload.put("pais", request.getPais());
		req_payload.put("rol", request.getRol());

		String response = this.doPostCall(req_payload, searchUsersByCriteria);

		if (response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, ReclutarSearchResult[].class);
		} catch (Exception e) {
			System.out.println("Error en Search Users by Criteria...");
			return null;
		}
	}

	public GenericResponse recruitPlayer(RecruitPlayerRequest request) {
		Map req_payload = new HashMap();
		req_payload.put("clanId", request.getGameId());
		req_payload.put("userId", request.getUserId());
		req_payload.put("description", request.getDescription());

		String response = this.doPostCall(req_payload, recruitPlayer);

		if (response == null || response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, GenericResponse.class);
		} catch (Exception e) {
			System.out.println("Error al actualizar game Profile para usuario " + request.getUserId());
			return null;
		}
	}
}
