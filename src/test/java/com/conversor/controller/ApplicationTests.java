package com.conversor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ApplicationTests {
	
	@Autowired
	MockMvc mock;

	@Test
	@Order(4)
	void testAltaMoneda() throws Exception{
		mock.perform(post("/moneda/save")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content("{\"descripcion\":\"Soles\",\"simbolo\": \"SOL\",\"valor\": 3.75,\"monedaBase\": \"False\"}"))    	
    			//.content(requestJson))
    			.andDo(print())
    			.andExpect(status().isCreated());
	}

}
