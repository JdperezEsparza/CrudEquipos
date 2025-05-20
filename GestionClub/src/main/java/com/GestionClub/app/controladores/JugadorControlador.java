package com.GestionClub.app.controladores;

import com.GestionClub.app.entidades.Jugador;
import com.GestionClub.app.servicios.JugadorServicio;

import java.util.Arrays;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugadores")
public class JugadorControlador {

    private final JugadorServicio jugadorService;


    public JugadorControlador(JugadorServicio jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("jugadores", jugadorService.obtenerTodosJugadores());
        return "listado-jugadores";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new Jugador());
        cargarPosiciones(model);
        return "formulario-jugadores";
    }

    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorService.guardarJugador(jugador);
        return "redirect:/jugadores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorService.obtenerJugadorPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        model.addAttribute("jugador", jugador);
        cargarPosiciones(model);
        return "formulario-jugadores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorService.eliminarJugador(id);
        return "redirect:/jugadores";
    }

    private void cargarPosiciones(Model model) {
        model.addAttribute("posiciones", Arrays.asList(
            "Portero", "Defensa", "Mediocampista", "Delantero"
        ));
    }
}