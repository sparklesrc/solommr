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
import com.solommr.model.GenericResponse;
import com.solommr.model.GenericResponse.SignUpRequest;
import com.solommr.model.SignUp.BasicRequest;
import com.solommr.model.SignUp.Pin;
import com.solommr.model.UserInfo.UserGameProfile;
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

	@Value("${projectrc.url.user.signUp}")
	private String signUp;

	@Value("${projectrc.url.user.resendCode}")
	private String resendCode;

	@Value("${projectrc.url.user.verifyCode}")
	private String verifyCode;

	@Value("${projectrc.url.user.gameProfile}")
	private String userGameProfile;

	@Value("${projectrc.url.user.updateGameProfile}")
	private String updateGameProfile;

	public UserInfo getUserInfoByMail(String mail) {
		Map req_payload = new HashMap();
		req_payload.put("mail", mail);

		String response = this.doPostCall(req_payload, findByMail);

		if (response == null || response.contains("Error")) {
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

		if (response == null || response.contains("Error")) {
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

	public GenericResponse signUp(SignUpRequest request) {
		Map req_payload = new HashMap();
		req_payload.put("email", request.getEmail());
		req_payload.put("password", request.getPassword());
		req_payload.put("edad", request.getEdad());
		req_payload.put("pais", request.getPais());
		req_payload.put("gameProfile", request.getGameProfile());

		String response = this.doPostCall(req_payload, signUp);

		if (response == null || response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, GenericResponse.class);
		} catch (Exception e) {
			System.out.println("Error al crear usuario " + request.getEmail());
			return null;
		}
	}

	public GenericResponse reSendCode(BasicRequest request) {
		Map req_payload = new HashMap();
		req_payload.put("mail", request.getMail());
		req_payload.put("code", null);

		String response = this.doPostCall(req_payload, resendCode);

		if (response == null || response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, GenericResponse.class);
		} catch (Exception e) {
			System.out.println("Error al crear usuario " + request.getMail());
			return null;
		}
	}

	public GenericResponse verifyCode(Pin request) {
		Map req_payload = new HashMap();
		req_payload.put("mail", request.getEmail());
		req_payload.put("code", request.getMyPin());

		String response = this.doPostCall(req_payload, verifyCode);

		if (response == null || response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, GenericResponse.class);
		} catch (Exception e) {
			System.out.println("Error al crear usuario " + request.getEmail());
			return null;
		}
	}

	public UserGameProfile getUserGameProfile(Long userId, Long gameId) {
		Map req_payload = new HashMap();
		req_payload.put("userId", userId);
		req_payload.put("gameId", gameId);

		String response = this.doPostCall(req_payload, userGameProfile);

		if (response == null || response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, UserGameProfile.class);
		} catch (Exception e) {
			System.out.println("Error al obtener Game Profile de usuario con Id " + userId);
			return null;
		}
	}

	public GenericResponse updateGameProfile(UserGameProfile request) {
		Map req_payload = new HashMap();
		req_payload.put("gameId", request.getGameId());
		req_payload.put("nickname", request.getNickname());
		req_payload.put("celular", request.getCelular());
		req_payload.put("description", request.getDescription());
		req_payload.put("roles", request.getRoles());
		req_payload.put("userId", request.getUserId());

		String response = this.doPostCall(req_payload, updateGameProfile);

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
