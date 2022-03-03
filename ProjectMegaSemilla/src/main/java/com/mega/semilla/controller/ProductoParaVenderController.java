package com.mega.semilla.controller;

import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mega.semilla.entidades.Producto;
import com.mega.semilla.entidades.ProductoParaVender;
import com.mega.semilla.entidades.ProductoVendido;
import com.mega.semilla.entidades.Venta;
import com.mega.semilla.repositorios.ProductoRepositorio;
import com.mega.semilla.repositorios.ProductosVendidosRepositorio;
import com.mega.semilla.repositorios.VentasRepositorio;
@RequestMapping("/vender")
@Controller
public class ProductoParaVenderController {

	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Autowired
	private VentasRepositorio ventasRepositorio;
	
	
	@Autowired
	private ProductosVendidosRepositorio productosVendidosRepositorio;
	

	private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
		ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession()
				.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
		}
		return carrito;
	}

	private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
		request.getSession().setAttribute("carrito", carrito);
	}
	

	private void limpiarCarrito(HttpServletRequest request) {
		this.guardarCarrito(new ArrayList<>(), request);
	}

	@GetMapping(value = "/")
	public String interfazVender(Model model, HttpServletRequest request) {
		model.addAttribute("producto", new Producto());
		float total = 0;
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		for (ProductoParaVender p : carrito)
			total += p.getTotal();
		model.addAttribute("total", total);
		return "vender";
	}

	@PostMapping(value = "/agregar")
	public String agregarAlCarrito(@ModelAttribute Producto producto, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		Producto productoBuscadoPorId = productoRepositorio.buscarPorId(producto.getId());
		if (productoBuscadoPorId == null) {
			redirectAttrs.addFlashAttribute("mensaje", "El producto con el código " + producto.getId() + " no existe")
					.addFlashAttribute("clase", "warning");
			return "redirect:/vender/";
		}
		if (productoBuscadoPorId.getCantidad() == null) {
			redirectAttrs.addFlashAttribute("mensaje", "El producto está agotado").addFlashAttribute("clase",
					"warning");
			return "redirect:/vender/";
		}
		boolean encontrado = false;
		for (ProductoParaVender productoParaVenderActual : carrito) {
			if (productoParaVenderActual.getId().equals(productoBuscadoPorId.getId())) {
				productoParaVenderActual.aumentarUnidadesVenta();
				encontrado = true;
				break;
			}
		}

		/*
		 * private Integer id; private String nombre; private float precio; private
		 * String categoria; private Integer cantidad; private Float unidadesVenta;
		 */
		if (!encontrado) {
			carrito.add(new ProductoParaVender(productoBuscadoPorId.getId(), productoBuscadoPorId.getNombre(),
					productoBuscadoPorId.getPrecio(), productoBuscadoPorId.getCategoria(),
					productoBuscadoPorId.getCantidad(), 1f));
		}
		this.guardarCarrito(carrito, request);
		return "redirect:/vender/";
	}

	@PostMapping(value = "/quitar/{indice}")
	public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
			carrito.remove(indice);
			this.guardarCarrito(carrito, request);
		}
		return "redirect:/vender/";
	}


	@GetMapping(value = "/limpiar")
	public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.limpiarCarrito(request);
		redirectAttrs.addFlashAttribute("mensaje", "Venta cancelada").addFlashAttribute("clase", "info");
		return "redirect:/vender/";
	}
	
	@PostMapping(value = "/terminar")
	public String terminarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
	    ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
	    // Si no hay carrito o está vacío, regresamos inmediatamente
	    if (carrito == null || carrito.size() <= 0) {
	        return "redirect:/vender/";
	    }
	    Venta v = ventasRepositorio.save(new Venta());
	    // Recorrer el carrito
	    for (ProductoParaVender productoParaVender : carrito) {
	        // Obtener el producto fresco desde la base de datos
	    	
	        Producto p = productoRepositorio.findById(productoParaVender.getId()).orElse(null);
	        if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
	        // Le restamos existencia
	        p.restarCantidad(productoParaVender.getUnidadesVenta());
	        // Lo guardamos con la existencia ya restada
	        Producto pAux = p;
	        productoRepositorio.deleteById(p.getId());
	        productoRepositorio.save(pAux);
	        // Creamos un nuevo producto que será el que se guarda junto con la venta

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
	private Float unidadesVenta;
    
    @ManyToOne
    @JoinColumn
    private Venta venta;

*/
	        ProductoVendido productoVendido = new ProductoVendido(productoParaVender.getId(),productoParaVender.getNombre(),productoParaVender.getPrecio(),productoParaVender.getCategoria(), productoParaVender.getCantidad(), productoParaVender.getUnidadesVenta(), v);
	        // Y lo guardamos
	        productosVendidosRepositorio.save(productoVendido);
	    }

	    // Al final limpiamos el carrito
	    this.limpiarCarrito(request);
	    // e indicamos una venta exitosa
	    redirectAttrs
	            .addFlashAttribute("mensaje", "Venta realizada correctamente")
	            .addFlashAttribute("clase", "success");
	    return "redirect:/vender/";
	}

}
