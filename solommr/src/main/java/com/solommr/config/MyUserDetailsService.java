package com.solommr.config;

import java.util.ArrayList;
import java.util.Collection;
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
		if (userInfo == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userInfo.getRol()));

		UserDetails userDetails = new User(userInfo.getMail(), userInfo.getPassword(), true, true, true,
				true, authorities);
		return userDetails;
	}

}
