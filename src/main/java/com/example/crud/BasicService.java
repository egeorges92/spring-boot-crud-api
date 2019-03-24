package com.example.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 
 * @param <T> type of entity
 */
public abstract class BasicService<T extends BasicEntity> {

	@Autowired
	private JpaRepository<T, Long> repository;

	public List<T> retrieveAll() {
		return repository.findAll();
	}

	public Optional<T> retrieve(Long id) {
		return repository.findById(id);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public T create(@RequestBody T entity) {
		return repository.save(entity);
	}

	/**
	 * Update an entity, controlling the existance.
	 * 
	 * @param id
	 *            entity id
	 * @param entity
	 *            the entity
	 * @return optional with updated entity if entity found, else empty optional.
	 */
	public Optional<T> update(Long id, T entity) {

		Optional<T> entityFound = repository.findById(id);
		if (entityFound.isPresent()) {
			entity.setId(id);
			return Optional.of(entity);
		} else {
			return Optional.empty();
		}
	}

}
