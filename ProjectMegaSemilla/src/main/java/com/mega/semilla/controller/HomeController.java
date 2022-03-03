package com.mega.semilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mega.semilla.errores.ErrorServicio;
import com.mega.semilla.servicios.UsuarioServicio;

//Controlador de Inicio - Home
@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping("/")
	public String inicio () {		
		return "inicio";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")//metodo para autorizar roles, los usuarios que esten registrados
	@GetMapping("/sesionactiva")
	public String sesion() {
		return "sesionactiva";
	}
	
	@GetMapping("/iniciar-sesion")
	public String inicios(@RequestParam(required = false) String error, ModelMap modelo) {//Este error es por si llega aparecer
		if (error != null) {															//email o contraseña invalida
			modelo.put("error", "Email o Clave incorrectos.");					  
		}

		return "iniciar-sesion";
	}
	
	
	@GetMapping("/crear-cuenta")
	public String crearCuenta() {
		return "crear-cuenta";
	}
	
	@GetMapping("/quienes-somos")
	public String conocernos() {
		return "quienes-somos";
	}
	
	@PostMapping("/registro")
	public String registro(ModelMap modelo, @RequestParam String nombre, @RequestParam String email, @RequestParam String username, @RequestParam String password) {
		try {
			usuarioServicio.registrar(email, password, nombre, username);
		} catch (ErrorServicio e) {

			modelo.put("error", e.getMessage());//Para indicar errores en la misma pagina web
			modelo.put("nombre", nombre); 		//Estos modelos.put(nombre)son para cuando
			modelo.put("email", email);			// te equivocas en algun parametro
			modelo.put("username", username);	//y en vez de borrar los datos ya ingresados, te los deja para que completes
			modelo.put("password", password);	// o arregles el que tenias incorrecto!

			return "crear-cuenta"; //Si esta todo mal xd, vuelve al registro 
		}
		
		modelo.put("titulo", "Bienvenido "+nombre+"!");
		modelo.put("descripcion","Su registro se ha completado de forma satisfactoria!");
		
		return "registroactivo"; //Si esta todo bien, nos va a mandar a la pagina de exito de registro (registroactivo.html)!
	}
	
}
