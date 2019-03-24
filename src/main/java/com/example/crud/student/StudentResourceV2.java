package com.example.crud.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.CrudResourceV2;

@RestController
@RequestMapping("/api/v2/students")
public class StudentResourceV2 extends CrudResourceV2<Student>{
		
}
