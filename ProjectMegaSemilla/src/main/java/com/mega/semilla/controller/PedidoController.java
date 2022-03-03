package com.mega.semilla.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mega.semilla.servicios.PedidoServicio;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	PedidoServicio pedidoServicio;
	
	@PostMapping("#")
	public String generarPedido(ModelMap modelo, @RequestParam String id, @RequestParam float importe, @RequestParam String estado, @RequestParam Date fecha, @RequestParam Boolean envio) {
		
		try {
			pedidoServicio.guardarPedido(id, importe, estado, fecha, envio);
			
		} catch (Exception e) {
			System.err.println("Ocurrio un error:" + e.getMessage());
			modelo.put("error", e.getMessage());
			return "error.html";
		}
		
		return "detalle_pedido.html"; //pedido realizado por el cliente
	}
	
	
}
