package com.solommr.util;

public enum EnumRolesCSGO {

	AWPER(1, "Awper"), 
	ENTRY_FRAGGER(2, "Entry Fragger"), 
	SUPPORT(3, "Support"), 
	LURKER(4, "Lurker"), 
	ASSAULT(5, "Assault");

	private Integer codigo;
	private String descripcion;

	EnumRolesCSGO(Integer codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}