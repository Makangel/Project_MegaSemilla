package com.mega.semilla.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mega.semilla.entidades.Producto;


@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{

}
