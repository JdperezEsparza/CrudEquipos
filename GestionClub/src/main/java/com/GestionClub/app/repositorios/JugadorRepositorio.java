package com.GestionClub.app.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GestionClub.app.entidades.Jugador;

public interface JugadorRepositorio extends JpaRepository<Jugador, Long> {
    List<Jugador> findAllById(Iterable<Long> ids);
    List<Jugador> findByClubIsNull();
    
}