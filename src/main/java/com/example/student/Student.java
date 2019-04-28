package com.example.student;

import java.util.Date;

import javax.persistence.Entity;

import com.example.person.Person;

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
public class Student extends Person {

	private String name;
	private String passportNumber;
	
	@Builder
	public Student(Long id, String firstName, String lastName, String email, Date birthday, String name, String passportNumber) {
		super(id, firstName, lastName, email, birthday);
		this.name = name;
		this.passportNumber = passportNumber;
	}
	
}
