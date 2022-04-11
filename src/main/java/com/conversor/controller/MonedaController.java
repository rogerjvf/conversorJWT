package com.conversor.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conversor.exceptions.BussniesRulesException;
import com.conversor.model.Moneda;
import com.conversor.service.MonedaServiceImpl;


@RestController
@RequestMapping("moneda")
@CrossOrigin(origins = "*")
public class MonedaController {
	
	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	MonedaServiceImpl serv;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Moneda> getById(@PathVariable Integer id) throws BussniesRulesException {
		return ResponseEntity.ok(serv.getById(id).get());  
	}
	
	@GetMapping("descripcion/{descripcion}")
	public ResponseEntity<Moneda> getByDescripcion(@PathVariable String descripcion) throws BussniesRulesException {
		log.info("en el controller metodo getByDescripcion");
		return ResponseEntity.ok(serv.getByDescripcion(descripcion).get());  
	}
	
	@GetMapping
	public ResponseEntity<List<Moneda>> getAll() throws Exception {		
		List<Moneda> monedas= serv.getAll();
		if(monedas.isEmpty())
			return new ResponseEntity<List<Moneda>>(monedas,null,HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Moneda>>(monedas,null,HttpStatus.OK);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Moneda> save(@RequestBody Moneda obj) throws BussniesRulesException {
		log.info("controller save, descripcion"+obj.getDescripcion());
		Moneda moneda=serv.save(obj);
		return new ResponseEntity<Moneda>(moneda,null,HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Boolean> update(@RequestBody  Moneda moneda) throws BussniesRulesException {
		serv.update(moneda);
		return new ResponseEntity<Boolean>(true, null, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) throws BussniesRulesException {
		serv.delete(id);
		return ResponseEntity.ok(true);
	}
	
	@DeleteMapping("descripcion/{descripcion}")
	public ResponseEntity<Boolean> deleteByDescription(@PathVariable String descripcion) throws BussniesRulesException {
		serv.deleteByName(descripcion);
		return ResponseEntity.ok(true);
	}
	
	
	@GetMapping("conversion/{idOrigen}/{idDestino}/{monto}")
	public ResponseEntity<BigDecimal> conversor(@PathVariable Integer idOrigen, @PathVariable Integer idDestino, @PathVariable BigDecimal monto ) throws BussniesRulesException {		
		log.info("controller conversor");
		BigDecimal montoResult=serv.conversor(idOrigen, idDestino, monto);
		return ResponseEntity.ok(montoResult);
	}

}
