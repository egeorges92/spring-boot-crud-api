package com.example.crud;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CrudResourceV3<T extends BasicEntity> {

	@Autowired
	protected BasicService<T> service;
	
	@Autowired
	private JpaRepository<T, Long> repository;

	@GetMapping
	public ResponseEntity<Page<T>> retrieveAll(Pageable pageable) {
		return ResponseEntity.ok(repository.findAll(pageable)); // add example support
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> retrieve(@PathVariable Long id) {
		T entity = service.retrieve(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return ResponseEntity.ok().body(entity);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> create(@RequestBody T entity) {
		T savedEntity = service.create(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEntity.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(path="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody T entity, @PathVariable Long id) {
		service.update(id, entity).orElseThrow(() -> new ResourceNotFoundException(id));
		return ResponseEntity.noContent().build();
	}
}
