package com.mega.semilla.entidades;

import javax.persistence.Entity;


public class ProductoParaVender extends Producto{
	
	private Float unidadesVenta;

/*
 * PRODUCTO:
 * @Id
   	private Integer id;
	private String nombre;
	private float precio;
	private String categoria; 
	private Integer cantidad;
 * 
 * ProductoParaVender:
	private Integer id;
	private String nombre;
	private float precio;
	private String categoria; 
	private Integer cantidad;
	private Float unidadesVenta;
	
	PRODUCTOvENDIDO:
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_vendido
    private Integer id;
    private String nombre;
    private Float precio; 
    private String categoria;
	private Integer cantidad;
    
    @ManyToOne
    @JoinColumn
    private Venta venta;

*/
	public ProductoParaVender(Integer id,String nombre, Float precio, String categoria, Integer cantidad, Float unidadesVenta) {
		super(id,nombre,precio,categoria,cantidad);
		this.unidadesVenta = unidadesVenta;
	}
	
	public ProductoParaVender(String nombre, Float precio, String categoria, Integer cantidad, Float unidadesVenta) {
		super(nombre,precio,categoria,cantidad);
		this.unidadesVenta = unidadesVenta;
	}
	
	public ProductoParaVender(Float unidadesVenta) {
		super();
		this.unidadesVenta = unidadesVenta;
	}
	
	public ProductoParaVender() {
		super();
		this.unidadesVenta = null;
	}
	
	public void aumentarUnidadesVenta() {
		this.unidadesVenta++;
	}
	
	public Float getUnidadesVenta() {
		return unidadesVenta;
	}
	
	public Float getTotal() {
		return this.getPrecio()*this.unidadesVenta;
	}

}
