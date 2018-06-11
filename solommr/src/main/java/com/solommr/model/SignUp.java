package com.solommr.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUp {

    @NotNull
    @Size(min=5, max=100)
	private String email;
    @NotNull
    @Size(min=6, max=10)
	private String password;
    @NotNull
    @Size(min=6, max=10)
	private String password2;
    @NotNull
    @Size(min=2, max=20)
	private String nickName;
    @NotNull
    @Min(5)
	private Integer edad;
    @NotNull
	private String pais;
    @NotNull
	private Integer game;

	public Integer getGame() {
		return game;
	}

	public void setGame(Integer game) {
		this.game = game;
	}

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
		private String email;

		public Integer getMyPin() {
			return myPin;
		}

		public void setMyPin(Integer myPin) {
			this.myPin = myPin;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	}

	public static class Country {
		private String codigo;
		private String descripcion;

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
	}

	public static class BasicRequest {
		private String mail;

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

	}
}
