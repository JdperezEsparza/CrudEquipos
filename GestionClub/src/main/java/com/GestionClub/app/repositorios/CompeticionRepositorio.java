package com.GestionClub.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GestionClub.app.entidades.Competicion;

public interface CompeticionRepositorio extends JpaRepository<Competicion, Long> {

}
