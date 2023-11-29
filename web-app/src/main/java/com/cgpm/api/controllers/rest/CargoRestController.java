package com.cgpm.api.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgpm.api.base.BaseRestController;
import com.cgpm.api.model.dto.CargoDTO;
import com.cgpm.api.model.entities.Cargo;
import com.cgpm.api.services.CargoService;

/**
 * @author michel.pech
 */

@RestController
@RequestMapping("/api/cargo")
public class CargoRestController extends BaseRestController<Cargo, Long, CargoDTO> {

	@Autowired
	@SuppressWarnings("unused")
	private CargoService service;
	
}
