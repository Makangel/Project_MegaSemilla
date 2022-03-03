package com.mega.semilla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	

public class CreateUserController {

	@RequestMapping("/crear-cuenta")
	public String crearCuenta() {
		return "crear-cuenta";
	}
	
	
}
