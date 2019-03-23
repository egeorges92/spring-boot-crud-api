package com.example.crud.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.CrudResource;

@RestController
@RequestMapping("/students")
public class StudentResource extends CrudResource<Student, StudentRepository>{
		
}
