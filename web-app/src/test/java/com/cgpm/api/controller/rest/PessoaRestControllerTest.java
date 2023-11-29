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

import com.cgpm.api.model.entities.Pessoa;
import com.cgpm.api.services.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author michel.pech
 */

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaRestControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PessoaService pessoaService;

	@Test
	void pessoaFindAll() throws Exception {
		// ARRANGE

		// ACT
		var response = mvc.perform(get("/api/pessoa")).andReturn().getResponse();

		// ASSERT
		Assertions.assertEquals(200, response.getStatus());
	}
	
	@Test
	void pessoaFindById() throws Exception {
		// ARRANGE
		String id = "999";

		// ACT
		var response = mvc.perform(get("/api/pessoa/{id}", id)).andReturn().getResponse();

		// ASSERT
		Assertions.assertEquals(404, response.getStatus());
	}
	
	@Test
	void pessoaInsert() throws Exception {
		// ARRANGE
		Pessoa pessoa = new Pessoa();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(pessoa);		
		
		// ACT
		var response = mvc.perform(post("/api/pessoa/")
						  .content(json)
						  .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();				

		// ASSERT
		Assertions.assertEquals(404, response.getStatus());
	}
	
	@Test
	void pessoaUpdate() throws Exception {
		// ARRANGE
		Pessoa pessoa = new Pessoa();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(pessoa);		

		// ACT
		var response = mvc.perform(put("/api/pessoa/")
			  	   		  .content(json)
			  	   		  .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();			
		// ASSERT
		Assertions.assertEquals(404, response.getStatus());
	}
	
	@Test
	void pessoaDeleteById() throws Exception {
		// ARRANGE
		String id = "999";

		// ACT
		var response = mvc.perform(delete("/api/pessoa/{id}", id)).andReturn().getResponse();

		// ASSERT
		Assertions.assertEquals(200, response.getStatus());
	}
}
