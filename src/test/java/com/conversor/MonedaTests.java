package com.conversor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.conversor.model.Moneda;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

@DisplayName("Test Moneda")
class MonedaTests {
	
	Moneda moneda;
	
	@BeforeEach
    void initMetodoTest(){
		moneda = new Moneda(); 
		moneda.setDescripcion("Dolar");
		moneda.setSimbolo("USD");
		moneda.setValor(new BigDecimal("1"));
		moneda.setMonedaBase(true);
    }

	@Test
	void testObject() {
		
		
		assertAll(
				()->assertEquals("Dolar", moneda.getDescripcion(), "Descripcion de la moneda no es la esperada"),
				()->assertEquals("USD", moneda.getSimbolo(), "Descripcion del Simbolo no es la esperada"),
				()->assertEquals(new BigDecimal("1"), moneda.getValor(), "El valor de Conversion no es el esperado"),
				()->assertEquals(true, moneda.isMonedaBase(), "Campo moneda base (referencia) no es el esperado")
				);		
	}
	
	
	@ParameterizedTest(name = "Registro numero {index}, leyendo la moneda {0} - {argumentsWithNames}")
    @CsvFileSource(resources = "/data.txt")
    void testDataCsvFile(String descripcionActual, String simboloActual,String valorActual,
    		String descripcionEsperado, String simboloEsperado,String valorEsperado) {
		
		Moneda monedaActual = new Moneda();
		monedaActual.setDescripcion(descripcionActual);
		monedaActual.setSimbolo(simboloActual);
		monedaActual.setValor(new BigDecimal(valorActual));
 
		assertAll(
				()->assertEquals(monedaActual.getDescripcion(), descripcionEsperado, "Descripcion de la moneda no es la esperada"),
				()->assertEquals(monedaActual.getSimbolo(), simboloEsperado, "Descripcion del Simbolo no es la esperada"),
				()->assertEquals(monedaActual.getValor(), new BigDecimal(valorEsperado), "El valor de Conversion no es el esperado")
				);		
    }

}
