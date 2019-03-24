package com.example.crud;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasicRepository<T extends BasicEntity> extends JpaRepository<T, Long> {
	
	/**
	 * Update an entity, controlling the existance.
	 * @param id entity id
	 * @param entity the entity
	 * @return optional with updated entity if entity found, else empty optional. 
	 */
	default Optional<T> update(Long id, T entity) {
		Optional<T> entityFound = this.findById(id);
		if (entityFound.isPresent()) {
			entity.setId(id);
			return Optional.of(entity);
		} else {
			return Optional.empty();
		}
	}
}
