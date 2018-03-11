package com.solommr.model;

public class UserInfo {

	private Long userId;
	private String steamId;
	private String mail;
	private String password;
	private String rol;
	private boolean isUserSyncWithSteam;

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

}
