package com.example.crud.student;

import javax.persistence.Entity;

import com.example.crud.BasicEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class Student extends BasicEntity {

	private String name;
	private String passportNumber;
	
	@Builder
	public Student(Long id, String name, String passportNumber) {
		super(id);
		this.name = name;
		this.passportNumber = passportNumber;
	}
	
	
}
