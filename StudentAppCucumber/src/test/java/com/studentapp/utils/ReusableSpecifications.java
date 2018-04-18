package com.studentapp.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class ReusableSpecifications {
	
	public static RequestSpecBuilder requestSpec;
	public static RequestSpecification  requestSpecification;
	
	public static ResponseSpecBuilder responseSpec;
	public static ResponseSpecification  responseSpecification;
	
	public static RequestSpecification getGenericRequestSpec() {
		
		requestSpec = new RequestSpecBuilder();
		requestSpec.setContentType(ContentType.JSON);		
		requestSpecification = requestSpec.build();
		return requestSpecification;	
	}
	
	public static ResponseSpecification getGenericResponseSpec() {
		
		responseSpec = new ResponseSpecBuilder();
		responseSpec.expectHeader("Content-Type", "application/json;charset=UTF-8");	
		responseSpec.expectHeader("Transfer-Encoding", "chunked");
		responseSpec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);	
		responseSpecification = responseSpec.build();
		return responseSpecification;
	}

}
