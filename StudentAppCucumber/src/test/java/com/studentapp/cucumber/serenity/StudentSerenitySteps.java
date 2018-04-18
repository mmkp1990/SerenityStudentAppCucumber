package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.serenityrestassured.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;

public class StudentSerenitySteps {

	@Step("Creating student with firstName: {0}, lastName: {1}, email: {2}, programme: {3} and courses: {4}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme,
			List<String> courses) {
			
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		return SerenityRest
		.rest()
		.given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then();
		
	}
	
	@Step("Getting the student information with first name: {0} ")
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName){
		
		return SerenityRest
				.rest()
				.given()
				.when()
				.get("/list")
				.then()
				.statusCode(200)
				.extract()
				.path("findAll{it.firstName=='"+firstName+"'}.get(0)");
	}
	
	@Step("Getting the student information by email: {0} ")
	public HashMap<String, Object> getStudentInfoByEmail(String email){
		
		return SerenityRest
				.rest()
				.given()
				.when()
				.get("/list")
				.then()
				.statusCode(200)
				.extract()
				.path("findAll{it.email=='"+email+"'}.get(0)");
	}
	
	@Step("Updating student information with studentId: {1}, firstName: {1}, lastName: {2}, email: {3}, programme: {4} and courses: {5}")
	public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email, String programme,
			List<String> courses) {
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		return SerenityRest
		.rest()
		.given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.put("/"+studentId)
		.then();
	}
	
	@Step("Delete student information with Id: {0}")
	public void DeleteStudent(int studentId) {
	
		 SerenityRest
		.rest()
		.given()
		.when()
		.delete("/" + studentId);
		
	}
	
	@Step("Getting student information with Id: {0}")
	public ValidatableResponse getStudentId(int studentId) {
		
	return SerenityRest
	.rest()
	.given()
	.when()
	.get("/" + studentId)
	.then();
			
	}
	
	@Step("Perform a GET request for all students")
	public ValidatableResponse getAllStudents() {
		
		return SerenityRest
		.rest()
		.given()
		.when()
		.get("/list")
		.then();
	}
}

