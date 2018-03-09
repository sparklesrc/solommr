package com.solommr.adapter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solommr.model.Game;

@Component
public class GameAdapter extends BaseAdapter {

	@Value("${projectrc.url.game.activeGames}")
	private String activeGames;

	public Game[] getActiveGames() {
		Map req_payload = new HashMap();

		String response = this.doPostCall(req_payload, activeGames);

		if (response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, Game[].class);
		} catch (Exception e) {
			System.out.println("Error Retrieving Active Games ");
			return null;
		}
	}
}
