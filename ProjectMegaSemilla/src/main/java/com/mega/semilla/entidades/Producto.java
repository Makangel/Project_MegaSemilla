package com.mega.semilla.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Producto {
	
	@Id
	private Integer id;
	private String nombre;
	private float precio;
	private String categoria; 
	
	private Integer cantidad;
	
}
