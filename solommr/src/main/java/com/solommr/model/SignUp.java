package com.solommr.model;

public class SignUp {

	private String email;
	private String password;
	private String password2;
	private String nickName;
	private Integer edad;
	private String pais;

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

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public static class Pin {
		private Integer myPin;

		public Integer getMyPin() {
			return myPin;
		}

		public void setMyPin(Integer myPin) {
			this.myPin = myPin;
		}

	}
}
