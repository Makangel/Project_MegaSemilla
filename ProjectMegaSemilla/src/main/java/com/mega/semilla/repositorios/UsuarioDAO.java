package com.mega.semilla.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mega.semilla.entidades.Usuario;

@Repository
public interface UsuarioDAO  extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email LIKE :email")
    public Usuario findByEmail(@Param("email") String email);

}