package com.example.crud;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CrudResource<T extends BasicEntity, R extends BasicRepository<T>> {

	@Autowired
	protected R repository;

	@GetMapping
	public ResponseEntity<List<T>> retrieveAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<T> retrieve(@PathVariable Long id) {
		Optional<T> entity = repository.findById(id);

		if (entity.isPresent()) {
			return ResponseEntity.of(entity);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> create(@RequestBody T entity) {
		T savedEntity = repository.save(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEntity.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(path="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody T entity, @PathVariable Long id) {

		Optional<T> updatedEntity = repository.update(id, entity);

		if (updatedEntity.isPresent()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
