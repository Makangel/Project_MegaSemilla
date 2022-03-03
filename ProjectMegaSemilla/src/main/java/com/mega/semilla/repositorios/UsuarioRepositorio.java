package com.mega.semilla.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mega.semilla.entidades.Usuario;

@Repository
public interface UsuarioRepositorio  extends JpaRepository<Usuario, String> {
    @Query("SELECT u FROM Usuario u WHERE u.email LIKE :email")
    public Usuario buscarPorMail(@Param("email") String email);

}