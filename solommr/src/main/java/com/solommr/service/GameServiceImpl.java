package com.solommr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.solommr.adapter.GameAdapter;
import com.solommr.model.Game;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameAdapter gameAdapter;

	@Override
	@Cacheable(value="activeGames", key = "#root.methodName")
	public Game[] getActiveGames() {
		return gameAdapter.getActiveGames();
	}

}
