package com.solommr.adapter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.solommr.model.ClanDataResponse;

@Component
public class ClanAdapter {

	@Autowired
	private RestTemplate restTemplate;

	public ClanDataResponse getClanData() {
		
		System.out.println("EN ADAPTER");
		
		Long gameId = 1L;
		String criteria = "aIR";
		String uri = "http://projectrc-pj-solo-mmr.7e14.starter-us-west-2.openshiftapps.com/projectrc/rest/team/search";

//		Map vars = new HashMap<String, String>();
//		vars.put("gameId", gameId);
//		vars.put("criteria", criteria);

		
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("gameId", gameId);
        req_payload.put("criteria", criteria);

        HttpEntity<?> request = new HttpEntity(req_payload, headers);

        ResponseEntity<?> response = new RestTemplate().postForEntity(uri, request, String.class);
        ClanDataResponse entityResponse = (ClanDataResponse) response.getBody();
        
        System.out.println("PRINT VALUES " + entityResponse.getClanName());
        
        return entityResponse;
	}
}
