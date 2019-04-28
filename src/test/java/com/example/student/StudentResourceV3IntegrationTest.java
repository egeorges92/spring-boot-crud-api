/**
 * 
 */
package com.example.student;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * @author egeorges
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class StudentResourceV3IntegrationTest {

	@Autowired
	private StudentRepository repository;

	@Value("${local.server.port}")
	private int serverPort;
	
	private static final String RESOURCE_PATH = "/api/v3/students";
	
	private Student STUDENT_1 = Student.builder().id(100l).firstName("Student1").lastName("STUDENT1").birthday(new Date()).name("Student1").passportNumber("111111").build();
	private Student STUDENT_2 = Student.builder().id(200l).firstName("Student2").lastName("STUDENT2").birthday(new Date()).name("Student2").passportNumber("222222").build();

	@Before
	public void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = serverPort;
		
		repository.deleteAll();
		STUDENT_1 = repository.save(STUDENT_1);
		STUDENT_2 = repository.save(STUDENT_2);
	}

	/**
	 * Test method for {@link com.example.crud.CrudResource#retrieveAll()}.
	 */
	@Test
	public void testRetrieveAll() {
		given()
		.get(RESOURCE_PATH)
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.OK.value())
		.and()
		.contentType(ContentType.JSON)
		.body("content.size()", is(2))
		.body("totalPages", is(1))
		.body("totalElements", is(2))
		;
	}

	/**
	 * Test method for
	 * {@link com.example.crud.CrudResource#retrieve(java.lang.Long)}.
	 */
	@Test
	public void testRetrieve() {
		given()
		.get("{path}/{id}", RESOURCE_PATH , STUDENT_1.getId())
		.then()
		.log().all()
		.assertThat()
		.statusCode(HttpStatus.OK.value())
		.and()
		.contentType(ContentType.JSON)
		.body("id", is(STUDENT_1.getId().intValue()))
		.body("name", is(STUDENT_1.getName()))
		.body("passportNumber", is(STUDENT_1.getPassportNumber()));
	}

	/**
	 * Test method for {@link com.example.crud.CrudResource#delete(long)}.
	 */
	@Test
	public void testDelete() {
		repository.save(STUDENT_1);
		given()
		.delete("{path}/{id}", RESOURCE_PATH , STUDENT_1.getId())
		.then()
		.assertThat()
		.statusCode(HttpStatus.NO_CONTENT.value());
		// Post control
		assertThat(repository.count()==1);
	}

	/**
	 * Test method for
	 * {@link com.example.crud.CrudResource#create(com.example.crud.BasicEntity)}.
	 */
	@Test
	public void testCreate() {
		String newStudent = "{\"name\":\"test\",\"passportNumber\":\"1234567890\"}";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(newStudent)
		.post(RESOURCE_PATH)
		.then()
		.assertThat()
		.statusCode(HttpStatus.CREATED.value());
		// Post control
		assertThat(repository.count()==3);
		assertThat(repository.findAll().get(2).getName().equals("test"));
	}

	/**
	 * Test method for
	 * {@link com.example.crud.CrudResource#update(com.example.crud.BasicEntity, java.lang.Long)}.
	 */
	@Test
	public void testUpdate() {
		String updatedStudent = "{\"name\":\"test\",\"passportNumber\":\"1234567890\"}";
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(updatedStudent)
		.put("{path}/{id}", RESOURCE_PATH , STUDENT_1.getId())
		.then()
		.assertThat()
		.statusCode(HttpStatus.NO_CONTENT.value());
		// Post control
		assertThat(repository.count()==2);
		assertThat(repository.findById(STUDENT_1.getId()).get().getName().equals("test"));
	}

}
