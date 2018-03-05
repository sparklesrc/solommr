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

@Component
public class ClanAdapter {

	@Autowired
	private RestTemplate restTemplate;

//	@Value("${projectrc.url.team.search}")
	private String teamSearch;

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
			System.out.println("ERROR EN MAPPING " + obj.getClanName());
		}

		System.out.println("PRINT VALUES " + obj.getClanName());

		return obj;
	}
}
