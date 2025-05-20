package com.GestionClub.app.controladores;

import com.GestionClub.app.entidades.Competicion;
import com.GestionClub.app.servicios.CompeticionServicio;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/competiciones")
public class CompeticionControlador {

    private final CompeticionServicio competicionService;

    public CompeticionControlador(CompeticionServicio competicionService) {
        this.competicionService = competicionService;
    }

    @GetMapping
    public String listarTodas(Model model) {
        model.addAttribute("competiciones", competicionService.obtenerTodasCompeticiones());
        return "listado-competiciones";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "formulario-competiciones";
    }

    @PostMapping("/guardar")
    public String guardarCompeticion(
            @ModelAttribute Competicion competicion,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        
        competicion.setFechaInicio(fechaInicio);
        competicion.setFechaFin(fechaFin);
        competicionService.guardarCompeticion(competicion);
        return "redirect:/competiciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Competicion competicion = competicionService.obtenerCompeticionPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        model.addAttribute("competicion", competicion);
        return "formulario-competiciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable Long id) {
        competicionService.eliminarCompeticion(id);
        return "redirect:/competiciones";
    }
}