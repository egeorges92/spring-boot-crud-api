package com.example.crud;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Main class that provide consistent behavior for all entities.
 * <ul>
 * Manage :
 * <li>id</li>
 * <li>created and update date<li>
 * <li>
 * 
 */
@MappedSuperclass
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class BasicEntity {

	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@JsonIgnore
	private Date created;
	
	@Getter
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@JsonIgnore
	private Date updated;
	
	/**
	 * Defalut constructor.
	 * @param id entity id.
	 */
	public BasicEntity(Long id) {
		super();
		this.id = id;
	}
	
	@PrePersist
	void onCreate() {
		Date now = new Date();
		this.created = now;
		this.updated = now;
	}
	
	@PreUpdate
	void onUpdate() {
		this.updated = new Date();
	}
}
