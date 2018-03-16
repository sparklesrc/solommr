package com.solommr.model;

import java.util.List;

public class UserInfo {

	private Long userId;
	private String steamId;
	private String mail;
	private String password;
	private String rol;
	private boolean isUserSyncWithSteam;
	private List<List<Integer>> userTeams;

	public String getSteamId() {
		return steamId;
	}

	public void setSteamId(String steamId) {
		this.steamId = steamId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isUserSyncWithSteam() {
		return isUserSyncWithSteam;
	}

	public void setUserSyncWithSteam(boolean isUserSyncWithSteam) {
		this.isUserSyncWithSteam = isUserSyncWithSteam;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<List<Integer>> getUserTeams() {
		return userTeams;
	}

	public void setUserTeams(List<List<Integer>> userTeams) {
		this.userTeams = userTeams;
	}

	public static class UserTeams {
		private Long gameId;
		private Long teamId;
		private Long memberTypeId;

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}

		public Long getTeamId() {
			return teamId;
		}

		public void setTeamId(Long teamId) {
			this.teamId = teamId;
		}

		public Long getMemberTypeId() {
			return memberTypeId;
		}

		public void setMemberTypeId(Long memberTypeId) {
			this.memberTypeId = memberTypeId;
		}

	}
}
