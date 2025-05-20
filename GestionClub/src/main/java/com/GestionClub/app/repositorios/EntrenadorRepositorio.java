package com.GestionClub.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GestionClub.app.entidades.Entrenador;

public interface EntrenadorRepositorio extends JpaRepository<Entrenador, Long> {

}
