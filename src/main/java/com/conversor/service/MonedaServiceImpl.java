package com.conversor.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.conversor.exceptions.BussniesRulesException;
import com.conversor.model.Moneda;
import com.conversor.repo.MonedaRepoI;

@Service
public class MonedaServiceImpl implements MonedaServiceI {
	
	Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	MonedaRepoI repo;
	
	@Override
	public Moneda save(Moneda moneda) throws BussniesRulesException {
		if(moneda.isMonedaBase()) {
			Optional<Moneda> monedaBase = repo.getMonedaBase();
			if(monedaBase.isPresent()) {
				throw new BussniesRulesException("1002","No pueden existir dos monedas base", HttpStatus.PARTIAL_CONTENT);
			}
		}
		//if(moneda.getDescripcion()==null || moneda.getValor()==null ) {
			//throw new BussniesRulesException("998","La descripcion o valor no pueden ser nulos", HttpStatus.BAD_REQUEST);
		//}
		return repo.save(moneda);
	}

	@Override
	public Optional<Moneda> getById(Integer id) throws BussniesRulesException {
		Optional<Moneda> moneda= repo.findById(id);
		if(moneda.isEmpty())
			throw new BussniesRulesException("1000","No Existe moneda asociada con ese id", HttpStatus.NO_CONTENT);
		
		return moneda;
	}

	@Override
	public List<Moneda> getAll() throws BussniesRulesException {
		List<Moneda> monedas = repo.findAll();
		if(monedas.isEmpty())
			throw new BussniesRulesException("1000","No Existen monedas asociadas", HttpStatus.NO_CONTENT);
		return repo.findAll();
	}

	@Override
	public void update(Moneda moneda) throws BussniesRulesException {
		if(moneda.isMonedaBase()) {
			Optional<Moneda> monedaBase = repo.getMonedaBase();
			if(monedaBase.isPresent()) {
				throw new BussniesRulesException("1002","No pueden existir dos monedas base", HttpStatus.PARTIAL_CONTENT);
			}
		}
		if(moneda.getDescripcion()==null || moneda.getValor()==null ) {
			throw new BussniesRulesException("998","La descripcion o valor no pueden ser nulos", HttpStatus.BAD_REQUEST);
		}
		repo.save(moneda);
	}

	@Override
	public void delete(Integer id) throws BussniesRulesException {
		if(getById(id).isPresent())
			repo.deleteById(id);
	}
	
	@Override
	public void deleteByName(String name) throws BussniesRulesException {
		Optional<Moneda> moneda= getByDescripcion(name);
		if(moneda.isPresent())
			repo.deleteById(moneda.get().getId());
		
	}
	
	@Override
	public Optional<Moneda> getByDescripcion(String descripcion) throws BussniesRulesException {
		log.info("En el service metodo getByDescripcion");
		Optional<Moneda> moneda= repo.getByName(descripcion);
		if(moneda.isEmpty())
			throw new BussniesRulesException("1000.1","No Existe moneda con la descripcion enviada", HttpStatus.NO_CONTENT);
		return moneda;
	}
	
	@Override
	public Optional<Moneda> getMonedaBase() {
		return repo.getMonedaBase();
	}
	
	@Override
	public BigDecimal conversor(Integer idOrigen, Integer IdDestino, BigDecimal monto) throws BussniesRulesException {
		if(idOrigen==IdDestino)
			throw new BussniesRulesException("999","No se puede realizar una transaccion a una misma moneda", HttpStatus.NO_CONTENT);
		
		if(monto.compareTo(BigDecimal.ZERO)<0 || monto.compareTo(BigDecimal.ZERO)==0 )
			throw new BussniesRulesException("1000","Monto a realizar conversion debe ser mayor a cero", HttpStatus.NO_CONTENT);
		
		Optional<Moneda> monedaBase = repo.getMonedaBase();
		if(monedaBase.isEmpty()) {
			throw new BussniesRulesException("1001","No Existe Moneda base, no se puede completar la operacion", HttpStatus.NO_CONTENT);
		}
			
		Optional<Moneda> origenMoneda = repo.findById(idOrigen);
		Optional<Moneda> destinoMoneda = repo.findById(IdDestino);

		if(origenMoneda.isEmpty() || origenMoneda.isEmpty()) {
			log.info("moneda de destino o origen no existe");
			throw new BussniesRulesException("1003","No Existe Moneda Origen o moneda Destino, no se puede completar la operacion", HttpStatus.NO_CONTENT);
		}	
			
		BigDecimal montoResult= BigDecimal.ZERO;
		if(origenMoneda.get().isMonedaBase()) {
			montoResult = monto.multiply(destinoMoneda.get().getValor());
		}else if(destinoMoneda.get().isMonedaBase()) {
			montoResult = monto.divide(origenMoneda.get().getValor(),2, RoundingMode.HALF_UP);
		}else {
			montoResult=monto.divide(origenMoneda.get().getValor(),2, RoundingMode.HALF_UP);
			montoResult= montoResult.multiply(destinoMoneda.get().getValor());
		}
		return montoResult;
	}

}
