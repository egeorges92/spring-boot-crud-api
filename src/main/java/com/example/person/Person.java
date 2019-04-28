package com.example.person;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;

import com.example.crud.BasicEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class Person extends BasicEntity{
	
	public Person(Long id, String firstName, String lastName, String email, Date birthday) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
	}

	private String firstName;
	
	private String lastName;
	
	private Date birthday;
	
	@Email
	private String email;

}
