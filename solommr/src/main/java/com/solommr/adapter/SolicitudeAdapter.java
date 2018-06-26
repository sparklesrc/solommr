package com.solommr.adapter;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solommr.model.GenericResponse.GenericResponse2;
import com.solommr.model.GenericResponse.InvitationsToTeamResponse;
import com.solommr.model.UserInfo.InvitationsTeamsRequest;

@Component
public class SolicitudeAdapter extends BaseAdapter {

	@Value("${projectrc.url.team.invitations}")
	private String teamInvitations;

	public GenericResponse2<InvitationsToTeamResponse> getInvitationTeams(InvitationsTeamsRequest request) {
		Map req_payload = new HashMap();
		req_payload.put("gameId", request.getGameId());
		req_payload.put("userId", request.getUserId());
		req_payload.put("solicitudeType", request.getSolicitudeType());

		String response = this.doPostCall(req_payload, teamInvitations);

		if (response == null || response.contains("Error")) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(response, GenericResponse2.class);
		} catch (Exception e) {
			System.out.println("Error al actualizar game Profile para usuario " + request.getUserId());
			return null;
		}
	}
}
