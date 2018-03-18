package com.solommr.adapter;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solommr.model.SteamCSGOProfile;
import com.solommr.model.SteamPlayerSummarie;
import com.solommr.model.UserInfo;

@Component
public class UserAdapter extends BaseAdapter {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${projectrc.url.user.findByMail}")
	private String findByMail;

	@Value("${projectrc.url.user.syncSteamUser}")
	private String syncSteamUser;

	@Value("${steam.url.csgo.profile}")
	private String steamCSGOProfile;

	@Value("${steam.key}")
	private String steamKey;

	@Value("${steam.url.profile}")
	private String playerSummarie;

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

	public SteamCSGOProfile getSteamProfile(String steamId) {
		URI targetUrl = UriComponentsBuilder.fromUriString(steamCSGOProfile).queryParam("appid", "730")
				.queryParam("key", steamKey).queryParam("steamid", steamId).build().encode().toUri();

		String result = restTemplate.getForObject(targetUrl, String.class);

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(result, SteamCSGOProfile.class);
		} catch (Exception e) {
			System.out.println("Error en Mapping obtener CSGO Steam Profile ");
			return null;
		}
	}

	public UserInfo syncSteamUser(Long userId, String steamId) {
		Map req_payload = new HashMap();
		req_payload.put("userId", userId);
		req_payload.put("steamId", steamId);

		String response = this.doPostCall(req_payload, syncSteamUser);

		if (response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, UserInfo.class);
		} catch (Exception e) {
			System.out.println("Error en Synchronize Steam UserId/SteamId " + userId + "/" + steamId);
			return null;
		}
	}

	public SteamPlayerSummarie getSteamPlayerSummarie(String steamId) {
		URI targetUrl = UriComponentsBuilder.fromUriString(playerSummarie).queryParam("key", steamKey)
				.queryParam("steamids", steamId).build().encode().toUri();

		String result = restTemplate.getForObject(targetUrl, String.class);

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(result, SteamPlayerSummarie.class);
		} catch (Exception e) {
			System.out.println("Error en Mapping obtener CSGO Steam Profile ");
			return null;
		}
	}
}
