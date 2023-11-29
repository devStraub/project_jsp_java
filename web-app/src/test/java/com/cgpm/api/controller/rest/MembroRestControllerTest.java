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

import com.cgpm.api.model.entities.Membro;
import com.cgpm.api.services.MembroService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author michel.pech
 */

@SpringBootTest
@AutoConfigureMockMvc
public class MembroRestControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private MembroService membroService;

	@Test
	void membroFindAll() throws Exception {
		// ARRANGE

		// ACT
		var response = mvc.perform(get("/api/membro")).andReturn().getResponse();

		// ASSERT
		Assertions.assertEquals(200, response.getStatus());
	}
	
	@Test
	void membroFindById() throws Exception {
		// ARRANGE		
		String id = "";

		// ACT
		var response = mvc.perform(get("/api/membro/{id}", id)).andReturn().getResponse();

		// ASSERT
		Assertions.assertEquals(404, response.getStatus());
	}
	
	@Test
	void membroInsert() throws Exception {
		// ARRANGE
		Membro membro = new Membro();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(membro);		
		
		// ACT
		var response = mvc.perform(post("/api/membro/")
						  .content(json)
						  .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();				

		// ASSERT
		Assertions.assertEquals(404, response.getStatus());
	}
	
	@Test
	void membroUpdate() throws Exception {
		// ARRANGE
		Membro membro = new Membro();

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(membro);

		// ACT
		var response = mvc.perform(put("/api/membro/")
			  	   		  .content(json)
			  	   		  .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();			
		// ASSERT
		Assertions.assertEquals(404, response.getStatus());
	}
	
	@Test
	void membroDeleteById() throws Exception {
		// ARRANGE
		String id = "999";

		// ACT
		var response = mvc.perform(delete("/api/membro/{id}", id)).andReturn().getResponse();

		// ASSERT
		Assertions.assertEquals(200, response.getStatus());
	}
}
