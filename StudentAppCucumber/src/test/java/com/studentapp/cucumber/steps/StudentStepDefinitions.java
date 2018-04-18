package com.studentapp.cucumber.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.utils.TestUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class StudentStepDefinitions {
	
	static String emailText = null;
	
	@Steps
	StudentSerenitySteps steps;
	
	@When("^User sends a GET request to the list endpoint, they must get back a valid status code 200$")
	public void verify_status_code_200_for_list_endpoint() {
		SerenityRest
		.rest()
		.given()
		.when()
		.get("/list")
		.then()
		.statusCode(200);
	}
	
	@When("^I create a new student by providing the information firstName (.*), lastName (.*), email (.*), programme (.*) and courses (.*)$")
	public void createStudent(String firstName, String lastName, String email, String programme, String course) {
		
	List<String> courses = new ArrayList<>();
	courses.add(course);
	emailText = TestUtils.getRandomValue()+email;
		
	steps.createStudent(firstName, lastName, emailText, programme, courses)
	.assertThat()
	.statusCode(201);
	}
	
	@Then("^I verify that the student with email containing (.*) is created$")
	public void verifyStudent(String email) {
		
		HashMap<String, Object> actualValue = steps.getStudentInfoByEmail(emailText);
		assertThat(actualValue, hasValue(emailText));
	}
		
			
}
