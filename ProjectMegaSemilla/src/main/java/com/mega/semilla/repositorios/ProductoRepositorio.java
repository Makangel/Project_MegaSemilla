package com.mega.semilla.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mega.semilla.entidades.Producto;


@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{
	 @Query("SELECT u FROM Producto u WHERE u.nombre LIKE :nombre")
	    public Producto buscarPorNombre(@Param("nombre") String nombre);
	 
	 @Query("SELECT u FROM Producto u WHERE u.id LIKE :id")
	    public Producto buscarPorId(@Param("id") Integer id);

}
