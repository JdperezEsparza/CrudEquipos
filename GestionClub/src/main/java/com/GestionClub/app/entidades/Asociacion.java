package com.GestionClub.app.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "asociaciones")
public class Asociacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String pais;
	private String presidente;
	private Integer añoFundacion;

	public Integer getAñoFundacion() {
		return añoFundacion;
	}

	public void setAñoFundacion(Integer añoFundacion) {
		this.añoFundacion = añoFundacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}
}
