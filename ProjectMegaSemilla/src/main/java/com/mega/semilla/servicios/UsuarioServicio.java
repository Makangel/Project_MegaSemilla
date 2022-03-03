package com.mega.semilla.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mega.semilla.entidades.Usuario;
import com.mega.semilla.errores.ErrorServicio;
import com.mega.semilla.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService{
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Transactional
	public void registrar(String email, String password, String nombre, String username) throws ErrorServicio {
		validar(email, password, nombre, username);	
		
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setNombre(nombre);
		usuario.setUsername(username);
//		usuario.setRol(Rol.USUARIO);//Todos los usuarios van a ser de tipo USUARIO, y por la base de datos colocamos ADMIN a quienes repongan
//									//los productos o mercaderia.
		
		String encriptada = new BCryptPasswordEncoder().encode(password);
		usuario.setPassword(encriptada);
		

		usuarioRepositorio.save(usuario);
	}
	
	@Transactional
	public void modificar(String id, String email, String password, String nombre, String username) throws ErrorServicio{
		validar(email, password, nombre, username);	
		
		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
		if (respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			usuario.setEmail(email);
			usuario.setNombre(nombre);
			usuario.setUsername(username);

			
			String encriptada = new BCryptPasswordEncoder().encode(password);
			usuario.setPassword(encriptada);
			
			usuarioRepositorio.save(usuario);
		} else {
			throw new ErrorServicio("No se encontró el usuario solicitado.");
		}
		
	}
	
	public Usuario buscarPorId(String id) throws ErrorServicio {
		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
		if (respuesta.isPresent()) {
			return respuesta.get();
		} else {
			throw new ErrorServicio("No se ha encontrado un Usuario para dicho ID");
		}
			
}
	
	
	private void validar(String email, String password, String nombre, String username) throws ErrorServicio {
		
//		validar(email, password, nombre, username);	
		

		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre del usuario no puede ser nulo");
		}

		if (username == null || username.isEmpty()) {
			throw new ErrorServicio("El Username del usuario no puede ser nulo");
		}

		if (email == null || email.isEmpty()) {
			throw new ErrorServicio("El mail del usuario no puede ser nulo");
		}

		if (password == null || password.isEmpty()) {
			throw new ErrorServicio("La clave del usuario no puede ser nula");
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
		if (usuario != null) {
			
			List<GrantedAuthority> permisos = new ArrayList<>();
			
			GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO"); //Pueden haber muchos permisos mas, pero este es uno de ellos como el de rol usuario registrado
			permisos.add(p1);
			
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true); 
			session.setAttribute("usuariosession", usuario); 
														
			User user = new User(usuario.getEmail(), usuario.getPassword(), permisos);
			return user;
		} else {
			return null;
		}
	}
	
	
}
