package com.mega.semilla.entidades;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Producto {
	
	@Id
	protected Integer id;
	protected String nombre;
	protected float precio;
	protected String categoria; 
	protected Integer cantidad;
	
	
	public Producto(Integer id, String nombre, float precio, String categoria, Integer cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.cantidad = cantidad;
	}
	
	public Producto(String nombre, float precio, String categoria, Integer cantidad) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.cantidad = cantidad;
	}

	public Producto() {
		super();
	}
	
	public void restarCantidad(Float unidad) {
        this.cantidad =(int) Math.round(cantidad - unidad);
    }
	
	
	
	
	
}
