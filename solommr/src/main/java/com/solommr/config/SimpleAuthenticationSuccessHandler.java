package com.solommr.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.solommr.model.UserInfo;
import com.solommr.service.UserService;

@Component
public class SimpleAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		UserInfo usuario = userService.getUserByMail(authentication.getName());

		if (usuario == null) {
			this.setDefaultTargetUrl("/login");
		}

		request.getSession().setAttribute("SESSION_USUARIO", usuario);

		System.out.println("CONTEXT " + request.getContextPath());
		if ("ADMIN".equals(usuario.getRol())) {
			System.out.println("USUARIO INICIA COMO ADMIN " + usuario.getMail());
			this.setDefaultTargetUrl("/admin");
		} else if ("USER".equals(usuario.getRol())) {
			System.out.println("USUARIO INICIA COMO USER " + usuario.getMail());
			this.setDefaultTargetUrl("/user");
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}

}
