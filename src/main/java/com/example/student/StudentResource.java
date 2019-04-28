package com.example.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.CrudResource;

@RestController
@RequestMapping("/api/v1/students")
public class StudentResource extends CrudResource<Student>{
		
}
