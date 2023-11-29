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
import com.cgpm.api.model.dto.MembroDTO;
import com.cgpm.api.model.dto.PessoaDTO;
import com.cgpm.api.model.entities.Membro;
import com.cgpm.api.model.entities.Projeto;
import com.cgpm.api.services.MembroService;

/**
 * @author michel.pech
 */

@RestController
@RequestMapping("/api/membro")
public class MembroRestController extends BaseRestController<Membro, Long, MembroDTO> {
	
	@Autowired
	@SuppressWarnings("unused")
	private MembroService service;	
	
	@Autowired
	private ModelMapper modelMapper;	
	
	@PostMapping("/pessoaByProjeto")
	public ResponseEntity<List<PessoaDTO>> findByProjeto(@RequestBody Projeto projeto) {
		List<Membro> entities = service.findByProjeto(projeto);
		List<MembroDTO> dtos = entities.stream().map(entity -> modelMapper.map(entity, MembroDTO.class))
	        	   							    .collect(Collectors.toList());		
		
		List<PessoaDTO> pessoaDTOList = dtos.stream().map(MembroDTO::getPessoa).collect(Collectors.toList());
		return ResponseEntity.ok().body(pessoaDTOList);
	}
}
