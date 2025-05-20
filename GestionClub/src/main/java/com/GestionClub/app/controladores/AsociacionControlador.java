package com.GestionClub.app.controladores;

import com.GestionClub.app.entidades.Asociacion;
import com.GestionClub.app.servicios.AsociacionServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionControlador {

    private final AsociacionServicio asociacionService;

    public AsociacionControlador(AsociacionServicio asociacionService) {
        this.asociacionService = asociacionService;
    }

    @GetMapping
    public String listarTodas(Model model) {
        model.addAttribute("asociaciones", asociacionService.obtenerTodasAsociaciones());
        return "listado-asociaciones";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "formulario-asociaciones";
    }

    @PostMapping("/guardar")
    public String guardarAsociacion(@ModelAttribute Asociacion asociacion) {
        asociacionService.guardarAsociacion(asociacion);
        return "redirect:/asociaciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Asociacion asociacion = asociacionService.obtenerAsociacionPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        model.addAttribute("asociacion", asociacion);
        return "formulario-asociaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAsociacion(@PathVariable Long id) {
        asociacionService.eliminarAsociacion(id);
        return "redirect:/asociaciones";
    }
}