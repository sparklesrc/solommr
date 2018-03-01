package com.solommr.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solommr.model.UserInfo;
import com.solommr.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		UserInfo userInfo = userService.getUserByMail(mail);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRol());
		UserDetails userDetails = (UserDetails) new User(userInfo.getMail(), userInfo.getPassword(), Arrays.asList(authority));
		return userDetails;
	}

}
