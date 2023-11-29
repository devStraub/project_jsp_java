package com.cgpm.api.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgpm.api.base.BaseRestController;
import com.cgpm.api.model.dto.ProjetoDTO;
import com.cgpm.api.model.entities.Projeto;
import com.cgpm.api.services.ProjetoService;

/**
 * @author michel.pech
 */

@RestController
@RequestMapping("/api/projeto")
public class ProjetoRestController extends BaseRestController<Projeto, Long, ProjetoDTO> {
	
	@Autowired
	@SuppressWarnings("unused")
	private ProjetoService service;
	
	
}
