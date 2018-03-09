package com.solommr.model;

import java.io.Serializable;

public class Game implements Serializable {

	private Integer gameId;
	private String name;

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
