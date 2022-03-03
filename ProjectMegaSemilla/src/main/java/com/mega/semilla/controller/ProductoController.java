package com.mega.semilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mega.semilla.entidades.Producto;
import com.mega.semilla.repositorios.ProductoRepositorio;
import com.mega.semilla.repositorios.UsuarioRepositorio;
import com.mega.semilla.servicios.ProductoServicio;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoServicio productoServicio;

	@Autowired
	private ProductoRepositorio productoRepositorio;

	@GetMapping("/agregar")
	public String agregarProducto(Model model) {
		model.addAttribute("producto", new Producto());
		return "agregarProducto";
	}


	@PostMapping("/agregar")
	public String guardarProducto(@ModelAttribute Producto producto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "agregarProducto";
		}
		if (productoRepositorio.buscarPorId(producto.getId()) != null) {
			return "redirect:agregarProducto";
		} else {
			productoRepositorio.save(producto);
			return "redirect:productoAgregado";
		}

	}
	
	
	@GetMapping("/productoAgregado")
	public String productoAgregado(Model model) {
		return "productoAgregado";
	}
	
	@GetMapping("/mostrar")
	public String mostrarProductos(Model model) {
	    model.addAttribute("productos", productoRepositorio.findAll());
	    return "ver-productos";
	}

	
	
	@GetMapping(value = "/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable int id, Model model) {
	    model.addAttribute("producto", productoRepositorio.findById(id).orElse(null));
	    return "editar-producto";
	}
	
	@PostMapping(value = "/editar/{id}")
	public String actualizarProducto(@ModelAttribute Producto producto, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        if (producto.getId() != null) {
	            return "editar-producto";
	        }
	        return "producto-editado-correctamente";
	    }
	    Producto posibleProductoExistente = productoRepositorio.buscarPorId(producto.getId());

	    if (posibleProductoExistente != null && !posibleProductoExistente.getId().equals(producto.getId())) {
	        
	        return "agregarProducto";
	    }
	    productoRepositorio.save(producto);
	    
	    return "producto-editado-correctamente";
	}
	
	@PostMapping(value = "/eliminar")
	public String eliminarProducto(@ModelAttribute Producto producto) {
	    productoRepositorio.deleteById(producto.getId());
	    return "producto-eliminado";
	}

	

}
