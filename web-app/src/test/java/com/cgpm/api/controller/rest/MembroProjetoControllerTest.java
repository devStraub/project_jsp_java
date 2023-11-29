package com.cgpm.api.controller.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cgpm.api.model.entities.MembroProjeto;
import com.cgpm.api.services.MembroProjetoService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author michel.pech
 */

@SpringBootTest
@AutoConfigureMockMvc
public class MembroProjetoControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private MembroProjetoService service;

	@Test
	void membroProjetoFindAll() throws Exception {
		// ARRANGE

		// ACT
		var response = mvc.perform(get("/api/membro_projeto")).andReturn().getResponse();

		// ASSERT
		Assertions.assertEquals(200, response.getStatus());
	}
	
	@Test
	void membroProjetoFindById() throws Exception {
		// ARRANGE		
		String id = "";

		// ACT
		var response = mvc.perform(get("/api/membro_projeto/{id}", id)).andReturn().getResponse();

		// ASSERT
		Assertions.assertEquals(404, response.getStatus());
	}
	
	@Test
	void membroProjetoInsert() throws Exception {
		// ARRANGE
		MembroProjeto membroProjeto = new MembroProjeto();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(membroProjeto);		
		
		// ACT
		var response = mvc.perform(post("/api/membro_projeto/")
						  .content(json)
						  .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();				

		// ASSERT
		Assertions.assertEquals(404, response.getStatus());
	}
	
	@Test
	void membroProjetoUpdate() throws Exception {
		// ARRANGE
		MembroProjeto membroProjeto = new MembroProjeto();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(membroProjeto);		

		// ACT
		var response = mvc.perform(put("/api/membro_projeto/")
			  	   		  .content(json)
			  	   		  .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();			
		// ASSERT
		Assertions.assertEquals(404, response.getStatus());
	}
	
	@Test
	void membroProjetoDeleteById() throws Exception {
		// ARRANGE
		String id = "999";

		// ACT
		var response = mvc.perform(delete("/api/membro_projeto/{id}", id)).andReturn().getResponse();

		// ASSERT
		Assertions.assertEquals(200, response.getStatus());
	}	
}
