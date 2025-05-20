package com.GestionClub.app.controladores;

import com.GestionClub.app.entidades.*;
import com.GestionClub.app.servicios.*;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/clubes")
public class ClubControlador {

    private final ClubServicio clubService;
    private final AsociacionServicio asociacionService;
    private final EntrenadorServicio entrenadorService;
    private final CompeticionServicio competicionService;
    private final JugadorServicio jugadorService;

    public ClubControlador(ClubServicio clubService, 
                          AsociacionServicio asociacionService,
                          EntrenadorServicio entrenadorService,
                          CompeticionServicio competicionService,
                          JugadorServicio jugadorService) {
        this.clubService = clubService;
        this.asociacionService = asociacionService;
        this.entrenadorService = entrenadorService;
        this.competicionService = competicionService;
        this.jugadorService = jugadorService;
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("clubes", clubService.obtenerTodosClubes());
        return "listado-clubes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("jugadores", jugadorService.obtenerJugadoresSinClub() != null 
                ? jugadorService.obtenerJugadoresSinClub() 
                : new ArrayList<>());
            cargarDatosRelacionados(model);
            return "formulario-clubes";
    }

    @PostMapping("/guardar")
    public String guardarClub(@ModelAttribute Club club) {
        // No necesitas lógica aquí, todo se maneja en el servicio
        clubService.guardarClub(club);
        return "redirect:/clubes";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Club club = clubService.obtenerClubPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        if (club.getJugadores() == null) club.setJugadores(new ArrayList<>());
        if (club.getCompeticiones() == null) club.setCompeticiones(new ArrayList<>());
        
        model.addAttribute("club", club);
        model.addAttribute("jugadores", jugadorService.obtenerJugadoresSinClub());
        cargarDatosRelacionados(model);
        return "formulario-clubes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarClub(@PathVariable Long id) {
        clubService.eliminarClub(id);
        return "redirect:/clubes";
    }

    private void cargarDatosRelacionados(Model model) {
        model.addAttribute("asociaciones", asociacionService.obtenerTodasAsociaciones());
        model.addAttribute("entrenadores", entrenadorService.obtenerTodosEntrenadores());
        model.addAttribute("competiciones", competicionService.obtenerTodasCompeticiones());
 
    }
}