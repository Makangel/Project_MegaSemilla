package com.mega.semilla.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mega.semilla.entidades.Usuario;
import com.mega.semilla.errores.ErrorServicio;
import com.mega.semilla.servicios.UsuarioServicio;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
	@GetMapping("/editar-perfil")
	public String editarPerfil(HttpSession session, @RequestParam String id, ModelMap model) {
		
		
		Usuario login = (Usuario) session.getAttribute("usuariosession"); //Con esta linea devolvemos el resultado de la session 
		if (login == null || !login.getId().equals(id)) { 			
			return "redirect:/iniciar-sesion"; 						
		}
		
		try {
			Usuario usuario = usuarioServicio.buscarPorId(id);
			model.addAttribute("perfil", usuario); //Para thymeleaf le estas diciendo que "perfil" es el usuario, en el html
												  
		} catch (ErrorServicio e) {
			model.addAttribute("error", e.getMessage());
		}
		
		return "editar-perfil"; //html
	}
	
	@PostMapping("/actualizar-perfil")
	public String actualizarPerfil(ModelMap modelo, HttpSession session, @RequestParam String id, @RequestParam String nombre, @RequestParam String username, @RequestParam String email, @RequestParam String password) {
		Usuario usuario = null;
		
		try {
			Usuario login = (Usuario) session.getAttribute("usuariosession"); 
			if (login == null || !login.getId().equals(id)) { 
				return "redirect:/iniciar-sesion"; 
			}
			
			usuario = usuarioServicio.buscarPorId(id);
			usuarioServicio.modificar(id, email, password, nombre, username);
			session.setAttribute("usuariosession", usuario);
			return "redirect:/";
		} catch (ErrorServicio e) {
			modelo.put("perfil", usuario);
			modelo.put("error", e.getMessage());
			
			return "editar-perfil";
		}
	} 
}
