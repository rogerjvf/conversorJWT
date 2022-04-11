package com.conversor.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.conversor.exceptions.BussniesRulesException;
import com.conversor.model.Moneda;

public interface MonedaServiceI {
	
	public Moneda save(Moneda moneda) throws BussniesRulesException;
	public Optional<Moneda> getById(Integer id) throws BussniesRulesException;
	public List<Moneda> getAll() throws BussniesRulesException;
	public void update(Moneda moneda) throws BussniesRulesException;
	public void delete(Integer id) throws BussniesRulesException;
	public BigDecimal conversor(Integer idOrigen, Integer IdDestino, BigDecimal monto) throws BussniesRulesException;
	public Optional<Moneda> getMonedaBase();
	void deleteByName(String name) throws BussniesRulesException;
	Optional<Moneda> getByDescripcion(String descripcion) throws BussniesRulesException;

}
