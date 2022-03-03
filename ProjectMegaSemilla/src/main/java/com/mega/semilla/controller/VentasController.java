package com.mega.semilla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mega.semilla.repositorios.VentasRepositorio;

@Controller
@RequestMapping(path = "/ventas")
public class VentasController {
    @Autowired
    VentasRepositorio ventasRepositorio;

    @GetMapping(value = "/")
    public String mostrarVentas(Model model) {
        model.addAttribute("ventas", ventasRepositorio.findAll());
        return "ventas/ver_ventas";
    }
}