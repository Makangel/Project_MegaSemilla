package com.mega.semilla.servicios;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import com.mega.semilla.entidades.Pedido;
import com.mega.semilla.errores.ErrorServicio;
import com.mega.semilla.repositorios.PedidoRepositorio;

@Service
public class PedidoServicio {
	@Autowired
	PedidoRepositorio pedidoRepo;
	
	@Transactional
	public void guardarPedido(String id, float importe, String estado, Date fecha, Boolean envio) throws ErrorServicio {
		validar(importe, estado, fecha, envio);//Fijarse si esta bien o no, o como se podria validar el guardarPedido
		
		Pedido pedido = new Pedido();
		pedido.setImporte(importe);
		pedido.setEstado(estado);
		pedido.setFecha(fecha);
		pedido.setEnvio(envio);
		
		pedidoRepo.save(pedido);
		
	}
	
	
	private void validar(float importe, String estado, Date fecha, Boolean envio) throws ErrorServicio{
			
	}
}
