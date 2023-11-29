package com.cgpm.api.controllers.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgpm.api.base.BaseRestController;
import com.cgpm.api.model.dto.MembroProjetoDTO;
import com.cgpm.api.model.dto.PessoaDTO;
import com.cgpm.api.model.entities.MembroProjeto;
import com.cgpm.api.model.entities.Projeto;
import com.cgpm.api.services.MembroProjetoService;

/**
 * @author michel.pech
 */

@RestController
@RequestMapping("/api/membro_projeto")
public class MembroProjetoRestController extends BaseRestController<MembroProjeto, Long, MembroProjetoDTO> {
	
	@Autowired
	@SuppressWarnings("unused")
	private MembroProjetoService service;
	
	@Autowired
	private ModelMapper modelMapper;		
	
	@PostMapping("/pessoaByProjeto")
	public ResponseEntity<List<PessoaDTO>> findByProjeto(@RequestBody Projeto projeto) {
		List<MembroProjeto> entities = service.findByProjeto(projeto);
		List<MembroProjetoDTO> dtos = entities.stream().map(entity -> modelMapper.map(entity, MembroProjetoDTO.class))
	        	   									   .collect(Collectors.toList());		
		
		List<PessoaDTO> pessoaDTOList = dtos.stream().map(MembroProjetoDTO::getPessoa).collect(Collectors.toList());				
		return ResponseEntity.ok().body(pessoaDTOList);
	}	
}
