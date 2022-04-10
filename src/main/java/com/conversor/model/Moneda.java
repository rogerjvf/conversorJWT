package com.conversor.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Moneda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "simbolo")
	private String simbolo;
	
	@NotNull
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
	@NotNull
	@Column(name = "moneda_base", nullable = false)
	private boolean monedaBase;
	
}
