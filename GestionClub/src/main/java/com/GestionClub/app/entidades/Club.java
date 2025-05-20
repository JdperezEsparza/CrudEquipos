package com.GestionClub.app.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;



@Entity
@Table(name = "clubes")
public class Club {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String colores;
	private String pais;
	private String ciudad;
	private String estadio;
	private String ultimoTitulo;
	
	@OneToOne
	private Entrenador entrenador;
	
	   @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Jugador> jugadores = new ArrayList<>();

	@ManyToOne
	private Asociacion asociacion;

	 @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinTable(
	        name = "club_competicion",
	        joinColumns = @JoinColumn(name = "club_id"),
	        inverseJoinColumns = @JoinColumn(name = "competicion_id")
	    )
	    private List<Competicion> competiciones = new ArrayList<>();
	 
	  @Transient
	    private List<Long> jugadoresIds; // Para recibir IDs del formulario

	    @Transient
	    private List<Long> competicionesIds; // Para recibir IDs del formulario

	    // Getter y Setters
	    public List<Long> getJugadoresIds() {
	        return jugadoresIds;
	    }

	    public void setJugadoresIds(List<Long> jugadoresIds) {
	        this.jugadoresIds = jugadoresIds;
	    }

	    public List<Long> getCompeticionesIds() {
	        return competicionesIds;
	    }

	    public void setCompeticionesIds(List<Long> competicionesIds) {
	        this.competicionesIds = competicionesIds;
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

	public String getColores() {
		return colores;
	}

	public void setColores(String colores) {
		this.colores = colores;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public String getUltimoTitulo() {
		return ultimoTitulo;
	}

	public void setUltimoTitulo(String ultimo_titulo) {
		this.ultimoTitulo = ultimo_titulo;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Asociacion getAsociacion() {
		return asociacion;
	}

	public void setAsociacion(Asociacion asociacion) {
		this.asociacion = asociacion;
	}

	public List<Competicion> getCompeticiones() {
		return competiciones;
	}

	public void setCompeticiones(List<Competicion> competiciones) {
		this.competiciones = competiciones;
	}


}
