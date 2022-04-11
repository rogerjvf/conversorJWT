package com.conversor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import com.conversor.dto.RolDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.conversor.model.Moneda;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class MonedaControllerTest {

    @Autowired
    private MockMvc  mvc;

    @Test
    @Order(1)
    @Disabled
    void testFindAll() throws Exception{
        mvc.perform(get("/moneda")).andDo(print());
    }
    
    /*@Test
    void testFindById() throws Exception{
        mvc.perform(get("/moneda/7")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(MockMvcResultMatchers.jsonPath("$.descripcion").value("Dolar"))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.simbolo").value("USD"))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.valor").value("1.0"));
    }*/
    
    /*@Test
    @ParameterizedTest(name = "Guardado numero {index}, leyendo la moneda {0} - {argumentsWithNames}")
    @CsvFileSource(resources = "/dataSave.txt")
    @Disabled
    void save(String descripcion, String simbolo, String valor, String monedaBase) throws Exception{
    	
    	Moneda monedaActual = new Moneda();
		monedaActual.setDescripcion(descripcion);
		monedaActual.setSimbolo(simbolo);
		monedaActual.setValor(new BigDecimal(valor));
		monedaActual.setMonedaBase(Boolean.parseBoolean(monedaBase));
        
        String requestJson = new ObjectMapper().writeValueAsString(monedaActual);
        
        System.out.println("imprimiendo el json: "+requestJson);
    	mvc.perform(post("/moneda")
    			.contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY29udmVyc2lvbnJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNjQ5NjUxNzE5LCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiI4YzI5NWI4Ni1jODkxLTQxOTItOGU2Yi0wMTdiYTdlZTM1ZDkiLCJjbGllbnRfaWQiOiJjb252ZXJzaW9ubWVkaWFwcCJ9.H8g9dlGmk2JsXDiN0xs4KK3a3Ej1z681cLqTkRWIi6k")
    			//.content("{\"descripcion\":\"Soles\",\"simbolo\": \"SOL\",\"valor\": 3.75,\"monedaBase\": \"False\"}"))    	
    			.content(requestJson))
    			.andDo(print())
    			.andExpect(status().isCreated());
    }*/

    /*
    @Test
    @ParameterizedTest(name = "Guardado numero {index}, leyendo la moneda {0} - {argumentsWithNames}")
    @CsvFileSource(resources = "/dataSave.txt")
    @Disabled
    void save(String id_rol, String descripcion, String nombre) throws Exception{

        RolDTO rolDTO = new RolDTO();
        rolDTO.setIdRol(id_rol);
        rolDTO.setDescripcion(descripcion);
        rolDTO.setNombre(nombre);
        String requestJson = new ObjectMapper().writeValueAsString(rolDTO);

        System.out.println("imprimiendo el json: "+requestJson);
        mvc.perform(post("/moneda")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY29udmVyc2lvbnJlc291cmNlaWQiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNjQ5NjUxNzE5LCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiI4YzI5NWI4Ni1jODkxLTQxOTItOGU2Yi0wMTdiYTdlZTM1ZDkiLCJjbGllbnRfaWQiOiJjb252ZXJzaW9ubWVkaWFwcCJ9.H8g9dlGmk2JsXDiN0xs4KK3a3Ej1z681cLqTkRWIi6k")
                //.content("{\"descripcion\":\"Soles\",\"simbolo\": \"SOL\",\"valor\": 3.75,\"monedaBase\": \"False\"}"))
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }*/
    
    @Test
    @ParameterizedTest(name = "Guardado numero {index}, leyendo la moneda {0} - {argumentsWithNames}")
    @CsvFileSource(resources = "/dataSave.txt")
    @Disabled
    void update(String descripcion, String simbolo, String valor, String monedaBase) throws Exception{
    	
    	Moneda monedaActual = new Moneda();
		monedaActual.setDescripcion(descripcion);
		monedaActual.setSimbolo(simbolo);
		monedaActual.setValor(new BigDecimal(valor));
		monedaActual.setMonedaBase(Boolean.parseBoolean(monedaBase));
        
        String requestJson = new ObjectMapper().writeValueAsString(monedaActual);
        
        System.out.println("imrimiendo el json: "+requestJson);
        
    	mvc.perform(put("/moneda")
    			.contentType(MediaType.APPLICATION_JSON)
    			//.content("{\"descripcion\":\"Soles\",\"simbolo\": \"SOL\",\"valor\": 3.75,\"monedaBase\": \"False\"}"))    	
    			.content(requestJson))
    			.andDo(print())
    			.andExpect(status().isOk());
    }
    
    
    

}