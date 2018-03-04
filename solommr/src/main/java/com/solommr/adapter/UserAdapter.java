package com.solommr.adapter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solommr.model.ClanDataResponse;
import com.solommr.model.UserInfo;

@Component
public class UserAdapter extends BaseAdapter {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${projectrc.url.user.findByMail}")
	private String findByMail;

	@Value("${steam.url.csgo.profile}")
	private String steamCSGOProfile;

	public UserInfo getUserInfoByMail(String mail) {
		Map req_payload = new HashMap();
		req_payload.put("mail", mail);

		String response = this.doPostCall(req_payload, findByMail);

		if (response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, UserInfo.class);
		} catch (Exception e) {
			System.out.println("Error en Mapping de Usuario " + mail);
			return null;
		}
	}

	public String getSteamProfile() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", "730");
		params.put("key", "");
		params.put("steamid", "76561198069746006");

		HttpEntity entity = new HttpEntity(headers);

		HttpEntity<String> response = restTemplate.exchange(steamCSGOProfile, HttpMethod.GET, entity, String.class, params);
		
		return response.getBody();
	}
}
