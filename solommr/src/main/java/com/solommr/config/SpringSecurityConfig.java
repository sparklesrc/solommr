package com.solommr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.antMatchers("**/user").access("hasRole('ROLE_USER')")
				.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll()
				.and().formLogin()
				.successHandler(successHandler)
				.loginPage("/login")
				.usernameParameter("username").passwordParameter("password")
				.and()
				.logout()
				.logoutSuccessUrl("/login?logout")
				.and().exceptionHandling().accessDeniedPage("/403");

		http.csrf().disable();
		http.headers().frameOptions().disable();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
	}

}
