package com.mega.semilla.entidades;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.mega.semilla.enumeraciones.Rol;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
     
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
    private String email;

    private String password;

    private String nombre;

    private String username;
    
	@Enumerated(EnumType.STRING)
	private Rol rol;
    
	

	public Usuario() {
		super();
		this.rol = Rol.USUARIO;
	}

	public Usuario(String id, String email, String password, String nombre, String username, Rol rol) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.username = username;
		this.rol = Rol.USUARIO;
	}

}