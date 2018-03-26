package com.solommr.util;

public enum EnumPais {

	ARGENTINA("AR", "Argentina", "011 54"),
	BOLIVIA("BO", "Bolivia", "011 591 54"),
	BRAZIL("BR", "Brazil", "011 55"),
	CHILE("CL", "Chile", "011 56"),
	COLOMBIA("CO", "Colombia", "011 57"),
	ECUADOR("EC", "Ecuador", "011 593"),
	PARAGUAY("PY", "Paraguay", "011 595"),
	PERU("PE", "Perú", "011 51"),
	URUGUAY("UY", "Uruguay", "011 598"),
	VENEZUELA("VE", "Venezuela", "011 58");

	private final String codigo;
	private final String descripcion;
	private final String codigoPais;

	EnumPais(String codigo, String descripcion, String codigoPais) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigoPais = codigoPais;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

}
