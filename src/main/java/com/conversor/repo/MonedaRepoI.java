package com.conversor.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.conversor.model.Moneda;

public interface MonedaRepoI extends JpaRepository<Moneda, Integer> {
	
	@Query(nativeQuery = false,value = " SELECT p FROM Moneda p WHERE monedaBase=true ")
    public Optional<Moneda> getMonedaBase();
	
	@Query(nativeQuery = false,value = " SELECT p FROM Moneda p WHERE descripcion=?1 ")
    public Optional<Moneda> getByName(String descripcion);

}
