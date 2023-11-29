package com.cgpm.api.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michel.pech
 */

@RestController
public abstract class BaseRestController<T extends PersistenceInterface<ID>, ID, DTO> {

	@Autowired
	private BaseService<T, ID> service;

	@Autowired
	private ModelMapper modelMapper;
	
	@SuppressWarnings("unchecked")
	private Class<DTO> getDTOClass() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<DTO>) parameterizedType.getActualTypeArguments()[2];
	}

	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}	

	@GetMapping
	public ResponseEntity<List<DTO>> findAll() {
		List<T> entities = service.findAll();
		List<DTO> dtos = entities.stream().map(entity -> modelMapper.map(entity, getDTOClass()))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DTO> findById(@PathVariable ID id) {
		Optional<T> entity = service.findById(id);
		if (entity.isPresent()) {
			DTO dto = modelMapper.map(entity.get(), getDTOClass());
			return ResponseEntity.ok().body(dto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<DTO> insert(@RequestBody DTO dto) {
		T entity = modelMapper.map(dto, getEntityClass());
		T savedEntity = service.insert(entity);
		DTO savedDto = modelMapper.map(savedEntity, getDTOClass());
		return ResponseEntity.ok().body(savedDto);
	}

	@PutMapping
	public ResponseEntity<DTO> update(@RequestBody DTO dto) {
		T entity = modelMapper.map(dto, getEntityClass());
		T updatedEntity = service.update(entity);
		DTO updatedDto = modelMapper.map(updatedEntity, getDTOClass());
		return ResponseEntity.ok().body(updatedDto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable ID id) {
		service.deleteById(id);
		return new ResponseEntity<>("Registro removido com sucesso!", HttpStatus.OK);
	}

}
