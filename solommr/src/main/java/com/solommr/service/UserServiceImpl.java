package com.solommr.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.solommr.adapter.UserAdapter;
import com.solommr.model.UserInfo;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserAdapter userAdapter;

	@Override
	public UserInfo getUserByMail(String mail) {
		System.out.println("En Service");
		return userAdapter.getUserInfoByMail(mail);
	}

}
