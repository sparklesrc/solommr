package com.solommr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solommr.adapter.ClanAdapter;
import com.solommr.model.ClanDataResponse;

@Service
public class ClanServiceImpl implements ClanService {

	@Autowired
	private ClanAdapter clanAdapter;

	@Override
	public ClanDataResponse getClanData() {
		System.out.println("EN SERVICE");
		return clanAdapter.getClanData();
	}

}
