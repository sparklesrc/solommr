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
	private String pais;
	private Integer edad;

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

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

	public static class UserGameProfile {
		private Integer gameId;
		private String nickname;
		private String celular;
		private String description;
		private String[] roles;
		private Long userId;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Integer getGameId() {
			return gameId;
		}

		public void setGameId(Integer gameId) {
			this.gameId = gameId;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getCelular() {
			return celular;
		}

		public void setCelular(String celular) {
			this.celular = celular;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String[] getRoles() {
			return roles;
		}

		public void setRoles(String[] roles) {
			this.roles = roles;
		}

	}

}
