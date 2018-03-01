package com.solommr.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

public class BaseAdapter {

	@Value("${projectrc.url.base}")
	private String urlBase;

	protected ResponseEntity<?> doPostCall() {
		return null;
	}
}
