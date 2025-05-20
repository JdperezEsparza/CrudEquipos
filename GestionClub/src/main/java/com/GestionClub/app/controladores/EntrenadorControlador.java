package com.GestionClub.app.controladores;

import com.GestionClub.app.entidades.Entrenador;
import com.GestionClub.app.servicios.EntrenadorServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorControlador {

    private final EntrenadorServicio entrenadorService;

    public EntrenadorControlador(EntrenadorServicio entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("entrenadores", entrenadorService.obtenerTodosEntrenadores());
        return "listado-entrenadores";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        cargarNacionalidades(model);
        return "formulario-entrenadores";
    }

    @PostMapping("/guardar")
    public String guardarEntrenador(@ModelAttribute Entrenador entrenador) {
        entrenadorService.guardarEntrenador(entrenador);
        return "redirect:/entrenadores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Entrenador entrenador = entrenadorService.obtenerEntrenadorPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        model.addAttribute("entrenador", entrenador);
        cargarNacionalidades(model);
        return "formulario-entrenadores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable Long id) {
        entrenadorService.eliminarEntrenador(id);
        return "redirect:/entrenadores";
    }

    private void cargarNacionalidades(Model model) {
        List<String> nacionalidades = Arrays.asList(
            "Alemana",
            "Argentina",
            "Belga",
            "Brasileña",
            "Canadiense",
            "Chilena",
            "Colombiana",
            "Costarricense",
            "Española",
            "Estadounidense",
            "Francesa",
            "Inglesa",
            "Italiana",
            "Japonesa",
            "Mexicana",
            "Holandesa",
            "Peruana",
            "Portuguesa",
            "Rusa",
            "Uruguaya",
            "Venezolana"
        );
        
      
        Collections.sort(nacionalidades);
        
        model.addAttribute("nacionalidades", nacionalidades);
    }
}