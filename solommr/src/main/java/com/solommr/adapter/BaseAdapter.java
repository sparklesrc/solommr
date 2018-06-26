package com.solommr.adapter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource("classpath:default.properties")
public class BaseAdapter {

//	@Value("${projectrc.url.base}")
	private String baseUrl = "http://localhost:8080/projectrc/rest";

	@Autowired
	RestTemplate restTemplate;

	public String doPostCall(Map req_payload, String url) {

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		Map map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		headers.setAll(map);

		HttpEntity<?> request = new HttpEntity(req_payload, headers);

		ResponseEntity<?> response = new RestTemplate().postForEntity(baseUrl + url, request, String.class);

		if(response != null && response.getStatusCodeValue() == 200){
			return (String) response.getBody();	
		}

		return "Error en llamada.";
	}

}
