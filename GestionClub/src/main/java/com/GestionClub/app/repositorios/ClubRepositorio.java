package com.GestionClub.app.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GestionClub.app.entidades.Club;

public interface ClubRepositorio extends JpaRepository<Club, Long>{
	Optional<Club> findByEntrenadorId(Long entrenadorId); 
}
