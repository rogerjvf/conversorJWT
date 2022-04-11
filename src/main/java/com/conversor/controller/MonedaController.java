package com.conversor.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import com.conversor.dto.AuditoriaDTO;
import com.conversor.dto.MonedaDTO;
import com.conversor.model.Auditoria;
import com.conversor.service.IAuditoriaService;
import com.conversor.service.impl.IAuditoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.conversor.service.impl.MonedaServiceImpl;

@RestController
@RequestMapping("moneda")
@CrossOrigin(origins = "*")
public class MonedaController {
	
	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	MonedaServiceImpl serv;

	@Autowired
	IAuditoriaService iAuditoriaService;
	
	
	@PreAuthorize("@restAuthServiceImpl.hasAccess('all')")
	@GetMapping("/{id}")
	public ResponseEntity<Moneda> getById(@PathVariable Integer id) throws BussniesRulesException {
		return ResponseEntity.ok(serv.getById(id).get());  
	}
	
	@PreAuthorize("@restAuthServiceImpl.hasAccess('all')")
	@GetMapping("descripcion/{descripcion}")
	public ResponseEntity<Moneda> getByDescripcion(@PathVariable String descripcion) throws BussniesRulesException {
		log.info("en el controller metodo getByDescripcion");
		return ResponseEntity.ok(serv.getByDescripcion(descripcion).get());  
	}
	
	//@PreAuthorize("hasAuthority('ADMIN')")
	@PreAuthorize("@restAuthServiceImpl.hasAccess('all')")
	@GetMapping
	public ResponseEntity<List<Moneda>> getAll() throws BussniesRulesException {
		List<Moneda> monedas= serv.getAll();
		if(monedas.isEmpty())
			return new ResponseEntity<>(monedas,null,HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(monedas,null,HttpStatus.OK);
	}
	
	@PreAuthorize("@restAuthServiceImpl.hasAccess('admin')")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Moneda> save(@RequestBody MonedaDTO obj) throws BussniesRulesException {
		log.info("controller save, descripcion"+obj.getDescripcion());
			Moneda moneda = serv.save(serv.MapMonedaDtoTOMoneda(obj));
			iAuditoriaService.registrarAuditoria(iAuditoriaService.getAuditoriaDTO("INSERT","ADMIN"));
			return new ResponseEntity<>(moneda,null,HttpStatus.CREATED);

	}
	
	@PreAuthorize("@restAuthServiceImpl.hasAccess('admin')")
	@PutMapping
	public ResponseEntity<Boolean> update(@RequestBody  MonedaDTO moneda) throws BussniesRulesException {
		serv.update(serv.MapMonedaDtoTOMoneda(moneda));
		iAuditoriaService.registrarAuditoria(iAuditoriaService.getAuditoriaDTO("UPDATE","ADMIN"));
		return new ResponseEntity<>(true, null, HttpStatus.OK);
	}
	
	@PreAuthorize("@restAuthServiceImpl.hasAccess('admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) throws BussniesRulesException {
		serv.delete(id);
		iAuditoriaService.registrarAuditoria(iAuditoriaService.getAuditoriaDTO("DELETE","ADMIN"));
		return ResponseEntity.ok(true);
	}
	
	@PreAuthorize("@restAuthServiceImpl.hasAccess('admin')")
	@DeleteMapping("descripcion/{descripcion}")
	public ResponseEntity<Boolean> deleteByDescription(@PathVariable String descripcion) throws BussniesRulesException {
		serv.deleteByName(descripcion);
		iAuditoriaService.registrarAuditoria(iAuditoriaService.getAuditoriaDTO("DELETE","ADMIN"));
		return ResponseEntity.ok(true);
	}
	

	@PreAuthorize("@restAuthServiceImpl.hasAccess('all')")
	@GetMapping("conversion/{idOrigen}/{idDestino}/{monto}")
	public ResponseEntity<String> conversor(@PathVariable Integer idOrigen, @PathVariable Integer idDestino, @PathVariable BigDecimal monto ) throws BussniesRulesException {		
		log.info("controller conversor");
		String montoResult=serv.conversor(idOrigen, idDestino, monto);
		return ResponseEntity.ok(montoResult);
	}

}
