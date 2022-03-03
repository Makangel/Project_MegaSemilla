package com.mega.semilla.servicios;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mega.semilla.entidades.Producto;
import com.mega.semilla.errores.ErrorServicio;
import com.mega.semilla.repositorios.ProductoRepositorio;

@Service
public class ProductoServicio {
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Transactional
	public void registrarProducto(Integer idProducto, String nombre, float precio, String categoria, Integer cantidad ) throws ErrorServicio {
		validar(nombre, precio, categoria, cantidad);
		
		Producto producto = new Producto();
		producto.setId(idProducto);
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setCategoria(categoria);
		producto.setCantidad(cantidad);
		
		productoRepositorio.save(producto);
		
	}
	
	@Transactional
	public void modificarProducto(Integer idProducto, String nombre, float precio, String categoria, Integer cantidad ) throws ErrorServicio {
		validar(nombre, precio, categoria, cantidad);
		
		Optional<Producto> respuesta = productoRepositorio.findById(idProducto);
		if (respuesta.isPresent()) {
			Producto producto = respuesta.get();
			producto.setNombre(nombre);
			producto.setPrecio(precio);
			producto.setCategoria(categoria);
			producto.setCantidad(cantidad);
			
			productoRepositorio.save(producto);
		} else {
			throw new ErrorServicio("No se encontró el producto solicitado.");
		}
	}
	
	@Transactional
	public void eliminarProducto(Integer idProducto) throws ErrorServicio {
	
		Optional<Producto> respuesta = productoRepositorio.findById(idProducto);
		if (respuesta.isPresent()) {
			Producto producto = respuesta.get();
			
			productoRepositorio.delete(producto);
		} else {
			throw new ErrorServicio("No se encontró el producto solicitado.");
		}
	}
	
	private void validar(String nombre, float precio, String categoria, Integer cantidad) throws ErrorServicio{
		
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre del producto no puede ser nulo");
		}
		
		if (precio < 0) {
			throw new ErrorServicio("El precio del producto no puede ser negativo");
		}
		
		if (categoria == null || categoria.isEmpty()) {
			throw new ErrorServicio("El nombre del producto no puede ser nulo");
		}
		
		if (cantidad < 0) {
			throw new ErrorServicio("La cantidad del producto no puede ser negativo");
		}		
	}

}
