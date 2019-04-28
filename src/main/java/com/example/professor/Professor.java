package com.example.professor;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import com.example.course.Course;
import com.example.person.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class Professor extends Person {

	private List<Course> instructs;
	
	@Builder
	public Professor(Long id, String firstName, String lastName, String email, Date birthday) {
		super(id, firstName, lastName, email, birthday);
	}
	
}