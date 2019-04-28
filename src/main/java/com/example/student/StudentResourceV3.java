package com.example.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.CrudResourceV3;

@RestController
@RequestMapping("/api/v3/students")
public class StudentResourceV3 extends CrudResourceV3<Student>{
		
}
