package com.GestionClub.app.servicios;

import com.GestionClub.app.entidades.Club;
import com.GestionClub.app.entidades.Competicion;
import com.GestionClub.app.entidades.Entrenador;
import com.GestionClub.app.entidades.Jugador;
import com.GestionClub.app.repositorios.ClubRepositorio;
import com.GestionClub.app.repositorios.CompeticionRepositorio;
import com.GestionClub.app.repositorios.EntrenadorRepositorio;
import com.GestionClub.app.repositorios.JugadorRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubServicio {

    @Autowired
    private ClubRepositorio clubRepository;
    @Autowired
    private EntrenadorRepositorio entrenadorRepository; // Añade esto
    @Autowired
    private JugadorRepositorio jugadorRepository; 
 
    @Autowired
    private CompeticionRepositorio competicionRepository;

    public List<Club> obtenerTodosClubes() {
        return clubRepository.findAll();
    }

    public Optional<Club> obtenerClubPorId(Long id) {
        return clubRepository.findById(id);
    }

    public Club guardarClub(Club club) {
        // 1. Manejar entrenador (validación 1-1)
        if (club.getEntrenador() != null && club.getEntrenador().getId() != null) {
            Entrenador entrenador = entrenadorRepository.findById(club.getEntrenador().getId())
                .orElseThrow(() -> new IllegalArgumentException("Entrenador no encontrado"));
            club.setEntrenador(entrenador);

            Optional<Club> clubExistente = clubRepository.findByEntrenadorId(entrenador.getId());
            if (clubExistente.isPresent() && !clubExistente.get().getId().equals(club.getId())) {
                throw new IllegalStateException("El entrenador ya está en otro club.");
            }
        }

        // 2. Guardar el Club primero (para generar ID)
        Club clubGuardado = clubRepository.save(club);

        // 3. Manejar jugadores (usando el club ya guardado)
        if (club.getJugadoresIds() != null && !club.getJugadoresIds().isEmpty()) {
            List<Jugador> jugadores = jugadorRepository.findAllById(club.getJugadoresIds());
            jugadores.forEach(jugador -> {
                jugador.setClub(clubGuardado); // Asignar el club persistido
                jugadorRepository.save(jugador);
            });
            // No es necesario setear la lista en el club (es el lado inverso)
        }

        // 4. Manejar competiciones
        if (club.getCompeticionesIds() != null && !club.getCompeticionesIds().isEmpty()) {
            List<Competicion> competiciones = competicionRepository.findAllById(club.getCompeticionesIds());
            clubGuardado.setCompeticiones(competiciones);
        }

        // 5. Actualizar el club con las competiciones (si es necesario)
        return clubRepository.save(clubGuardado);
    }



    public void eliminarClub(Long id) {
        clubRepository.deleteById(id);
    }
}