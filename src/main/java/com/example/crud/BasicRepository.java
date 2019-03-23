package com.example.crud;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasicRepository<T extends BasicEntity> extends JpaRepository<T, Long> {
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
