package com.GestionClub.app.servicios;

import com.GestionClub.app.entidades.Asociacion;
import com.GestionClub.app.repositorios.AsociacionRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsociacionServicio {

    private final AsociacionRepositorio asociacionRepositorio;

    public AsociacionServicio(AsociacionRepositorio asociacionRepository) {
        this.asociacionRepositorio = asociacionRepository;
    }

    // Obtener todas las asociaciones
    public List<Asociacion> obtenerTodasAsociaciones() {
        return asociacionRepositorio.findAll();
    }

    // Obtener una asociación por ID
    public Optional<Asociacion> obtenerAsociacionPorId(Long id) {
        return asociacionRepositorio.findById(id);
    }

    // Guardar o actualizar una asociación
    public Asociacion guardarAsociacion(Asociacion asociacion) {
        return asociacionRepositorio.save(asociacion);
    }

    // Eliminar una asociación
    public void eliminarAsociacion(Long id) {
        asociacionRepositorio.deleteById(id);
    }
}