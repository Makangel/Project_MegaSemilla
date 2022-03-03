package com.mega.semilla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/iniciar-sesion")
	public String IniciarSesion() {
		return "iniciar-sesion";
		
	}
}
