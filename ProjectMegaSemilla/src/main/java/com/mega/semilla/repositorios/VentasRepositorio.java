package com.mega.semilla.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.mega.semilla.entidades.Venta;

public interface VentasRepositorio extends CrudRepository<Venta, Integer> {
}