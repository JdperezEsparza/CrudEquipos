package com.GestionClub.app.servicios;

import com.GestionClub.app.entidades.Entrenador;
import com.GestionClub.app.repositorios.EntrenadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorServicio {

    @Autowired
    private EntrenadorRepositorio entrenadorRepository;

    public List<Entrenador> obtenerTodosEntrenadores() {
        return entrenadorRepository.findAll();
    }

    public Optional<Entrenador> obtenerEntrenadorPorId(Long id) {
        return entrenadorRepository.findById(id);
    }

    public Entrenador guardarEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public void eliminarEntrenador(Long id) {
        entrenadorRepository.deleteById(id);
    }
}