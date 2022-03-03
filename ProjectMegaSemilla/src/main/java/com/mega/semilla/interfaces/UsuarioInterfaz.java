package com.mega.semilla.interfaces;


import org.springframework.stereotype.Service;

import com.mega.semilla.entidades.Usuario;


@Service
public interface UsuarioInterfaz {
	public Usuario findByEmail(String email);
	public Usuario registrar(Usuario u);
}