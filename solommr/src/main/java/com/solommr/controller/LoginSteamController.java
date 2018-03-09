package com.solommr.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.expressme.openid.Association;
import org.expressme.openid.Authentication;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdException;
import org.expressme.openid.OpenIdManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.solommr.config.SteamOpenID;

@Controller
public class LoginSteamController {

//	private final SteamOpenID openid = new SteamOpenID();
//
//	private String getFullUrl(HttpServletRequest request, String path) {
//		StringBuilder builder = new StringBuilder("localhost:8080/solommr");
//		builder.insert(0, "http://");
//		builder.append(path);
//		return builder.toString();
//	}
//
//	@GetMapping("/trade")
//	public String trade(HttpServletRequest request, HttpServletResponse response) {
//		String redirectTo = openid.login(getFullUrl(request, "/auth"));
//		return "redirect:/" + redirectTo;
//		// We should never return here.
//		// The OpenID login provider should take us somewhere else!
//		// halt(403, "Go Away!");
//		// return null;
//	}
//
//	@GetMapping("/auth")
//	public String auth(HttpServletRequest request, HttpServletResponse response) {
//		String values = request.getQueryString();
//		System.out.println("Values :: " + values);
//		String user = openid.verify(request.getRequestURL().toString(), null); // request.queryMap().toMap()
//		String fullUrl = getFullUrl(request, "/");
//		if (user == null) {
//			return "redirect:/" + fullUrl;
//		}
//		request.getSession().setAttribute("STEAM_USUARIO", user);
//		return "redirect:/" + fullUrl;
//	}

	private OpenIdManager manager;

	static final long ONE_HOUR = 3600000L;
	static final long TWO_HOUR = ONE_HOUR * 2L;
	static final String ATTR_MAC = "openid_mac";
	static final String ATTR_ALIAS = "openid_alias";

	@GetMapping("/trade")
	public String trade(HttpServletRequest request, HttpServletResponse response) {
		manager = new OpenIdManager();
	    manager.setRealm("http://localhost:8080/solommr/auth");
	    manager.setReturnTo("http://localhost:8080/solommr/auth");

		Endpoint endpoint = manager.lookupEndpoint("http://steamcommunity.com/openid");
        Association association = manager.lookupAssociation(endpoint);
        request.getSession().setAttribute(ATTR_MAC, association.getRawMacKey());
        request.getSession().setAttribute(ATTR_ALIAS, endpoint.getAlias());
        String url = manager.getAuthenticationUrl(endpoint, association);
        return "redirect:/" + url;
	}

	@GetMapping("/auth")
	public String auth(HttpServletRequest request, HttpServletResponse response) {
        checkNonce(request.getParameter("openid.response_nonce"));
        byte[] mac_key = (byte[]) request.getSession().getAttribute(ATTR_MAC);
        String alias = (String) request.getSession().getAttribute(ATTR_ALIAS);
        Authentication authentication = manager.getAuthentication(request, mac_key, alias);
        authentication.getFirstname();
        response.setContentType("text/html; charset=UTF-8");
        return "redirect:/user";
	}

	private void checkNonce(String nonce) {
	    // check response_nonce to prevent replay-attack:
	    if (nonce==null || nonce.length()<20)
	        throw new OpenIdException("Verify failed.");
	    // make sure the time of server is correct:
	    long nonceTime = getNonceTime(nonce);
	    long diff = Math.abs(System.currentTimeMillis() - nonceTime);
	    if (diff > ONE_HOUR)
	        throw new OpenIdException("Bad nonce time.");
	}

	private long getNonceTime(String nonce) {
	    try {
	        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
	                .parse(nonce.substring(0, 19) + "+0000")
	                .getTime();
	    }
	    catch(ParseException e) {
	        throw new OpenIdException("Bad nonce time.");
	    }
	}

}
