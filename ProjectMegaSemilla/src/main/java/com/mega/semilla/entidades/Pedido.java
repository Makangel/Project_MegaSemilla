package com.mega.semilla.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Pedido {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private Float importe;
	private String estado; //en deposito, local, bla bla bla
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private Boolean envio; //definir si lo queremos retirar del local o mandarlo a domicilio
	@OneToMany
	private List<PedidoDetalle> detalles;
	
}
