package com.mega.semilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mega.semilla.servicios.ProductoServicio;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	ProductoServicio productoServicio;
	
	@PostMapping("/guardar")
	public String guardarProd(ModelMap modelo, @RequestParam Integer idProducto, @RequestParam String nombre, @RequestParam float precio, @RequestParam String categoria, @RequestParam Integer cantidad) {
		
		System.out.println("Id: " + idProducto);
		System.out.println("Nombre: " + nombre);
		System.out.println("Categoría: " + categoria);
		System.out.println("Precio: " + precio);
		System.out.println("Stock Total: " + cantidad);

		try {
			productoServicio.registrarProducto(idProducto, nombre, precio, categoria, cantidad);
			
		} catch (Exception e) {
			System.err.println("Ocurrio un error:" + e.getMessage());
			modelo.put("error", e.getMessage());
			return "error.html";
		}
		
		return "#"; //hay que hacer html para retornar
	}
	
	@PostMapping("/modificar")
	public String modificar(ModelMap modelo, @RequestParam Integer idProducto, @RequestParam String nombre, @RequestParam float precio, @RequestParam String categoria, @RequestParam Integer cantidad) {
		
		System.out.println("Id: " + idProducto);
		System.out.println("Nombre: " + nombre);
		System.out.println("Categoría: " + categoria);
		System.out.println("Precio: " + precio);
		System.out.println("Stock Total: " + cantidad);

			try {
				productoServicio.modificarProducto(idProducto, nombre, precio, categoria, cantidad);
			} catch (Exception e) {
				System.err.println("Ocurrio un error:" + e.getMessage());
				modelo.put("error", e.getMessage());
				return "error.html";
			}
			return "#";
		}
	
	@PostMapping("/eliminar")
	public String eliminar(ModelMap modelo, @RequestParam Integer idProducto) {
		
	//	System.out.println("Id: " + idProducto);

		try {
			productoServicio.eliminarProducto(idProducto);
		} catch (Exception e) {
			System.err.println("Ocurrio un error:" + e.getMessage());
			modelo.put("error", e.getMessage());
			return "error.html";
		}
		return "#";
	}
	
}
