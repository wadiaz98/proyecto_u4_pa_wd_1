package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modelo.Persona;
import com.example.demo.service.IPersonaService;

@Controller
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService personaService;

	@GetMapping("/nuevaPersona")
	public String nuevaPersona(Persona persona) {
		return "vistaNuevaPersona";

	}

	@PostMapping("/insertar")
	public String insertarPersona(Persona persona) {
		this.personaService.guardar(persona);
		return "guardado";
	}

	@GetMapping("/buscar")
	public String buscarTodos(Model model) {
		List<Persona> lista = this.personaService.buscarTodos();
		model.addAttribute("personas", lista);
		return "vistaListaPersona";
	}
	
	@DeleteMapping("/borrar/{id}")
	public String borrarPersona(@PathVariable("id") Integer id) {
		this.personaService.eliminar(id);
		return "redirect:/personas/buscar";
	}
	
	@GetMapping("/buscarPorId/{id}")
	public String buscarPorId(@PathVariable("id") Integer id, Model model) {
		Persona persona = this.personaService.buscarPorId(id);
		model.addAttribute("persona", persona);
		return "vistaPersona";
	}
	
	@PutMapping("/actualizar/{id}")
	public String actualizarPorId(@PathVariable("id") Integer id, Persona persona) {
		persona.setId(id);
		this.personaService.actualizar(persona);
		return "redirect:/personas/buscar";
	}
}
