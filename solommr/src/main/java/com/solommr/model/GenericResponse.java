package com.solommr.model;

public class GenericResponse {

	private String msg;
	private boolean state;

	public GenericResponse() {

	}

	public GenericResponse(String msg) {
		this.msg = msg;
	}

	public GenericResponse(Boolean state) {
		this.msg = "ok";
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public static class SignUpRequest {
		private String email;
		private String password;
		private Integer edad;
		private String pais;
		private SignUpGameProfile gameProfile;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

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

		public SignUpGameProfile getGameProfile() {
			return gameProfile;
		}

		public void setGameProfile(SignUpGameProfile gameProfile) {
			this.gameProfile = gameProfile;
		}

	}

	public static class SignUpGameProfile {
		// extra values
		private Integer gameId;
		private String nickname;
		private String celular;
		private String description;
		private String[] roles;

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

		public String[] getRoles() {
			return roles;
		}

		public void setRoles(String[] roles) {
			this.roles = roles;
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

	}
}
