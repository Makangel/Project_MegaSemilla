package com.mega.semilla.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductoVendido{
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_vendido;
	private Integer id;
	private String nombre;
	private float precio;
	private String categoria; 
	private Integer cantidad;
	private Float unidadesVenta;
    @ManyToOne
    @JoinColumn
    private Venta venta;
    
    
    /*
      * ProductoParaVender:
	private Integer id;
	private String nombre;
	private float precio;
	private String categoria; 
	private Integer cantidad;
	private Float unidadesVenta;
     */
    public ProductoVendido(Integer id, String nombre, float precio, String categoria, Integer cantidad, Float unidadesVenta, Venta venta) {
        this.id=id;
        this.nombre=nombre;
        this.precio=precio;
        this.categoria=categoria;
        this.cantidad=cantidad;
        this.unidadesVenta=unidadesVenta;
        this.venta = venta;
    }
    
    public ProductoVendido(Venta venta) {
        
        this.venta = venta;
    }
    
    public ProductoVendido() {
    }

    public Float getTotal() {
        return this.cantidad * this.precio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}