package com.GestionClub.app.servicios;



import com.GestionClub.app.entidades.Competicion;
import com.GestionClub.app.repositorios.CompeticionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompeticionServicio {

    @Autowired
    private CompeticionRepositorio competicionRepository;

    public List<Competicion> obtenerTodasCompeticiones() {
        return competicionRepository.findAll();
    }

    public Optional<Competicion> obtenerCompeticionPorId(Long id) {
        return competicionRepository.findById(id);
    }

    public Competicion guardarCompeticion(Competicion competicion) {
        return competicionRepository.save(competicion);
    }

    public void eliminarCompeticion(Long id) {
        competicionRepository.deleteById(id);
    }
}
