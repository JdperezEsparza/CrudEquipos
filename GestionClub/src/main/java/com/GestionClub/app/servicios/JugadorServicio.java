package com.GestionClub.app.servicios;

import com.GestionClub.app.entidades.Jugador;
import com.GestionClub.app.repositorios.JugadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JugadorServicio {

    @Autowired
    private JugadorRepositorio jugadorRepository;

    public List<Jugador> obtenerTodosJugadores() {
        return jugadorRepository.findAll();
    }

    public Optional<Jugador> obtenerJugadorPorId(Long id) {
        return jugadorRepository.findById(id);
    }

    public Jugador guardarJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public void eliminarJugador(Long id) {
        jugadorRepository.deleteById(id);
    }
    
    public List<Jugador> obtenerJugadoresSinClub() {
        List<Jugador> jugadores = jugadorRepository.findByClubIsNull();
        return jugadores != null ? jugadores : new ArrayList<>(); // Nunca retorna null
    }
    
    
}
