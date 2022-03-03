package com.mega.semilla.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mega.semilla.entidades.Pedido;
import com.mega.semilla.entidades.Producto;

public interface PedidoRepositorio extends JpaRepository<Pedido, String>{

	 @Query("SELECT u FROM Pedido u WHERE u.id LIKE :id")
	    public Producto buscarPorId(@Param("id") String id);
	
}	
