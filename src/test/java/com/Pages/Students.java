package com.Pages;

import static org.testng.Assert.assertThrows;

import java.util.HashMap;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Students {
	String groupName = "Group:4";
	static Response response;
	String endPoints = "http://localhost:3000/Students/";
	String ep_WithId = "http://localhost:3000/Students?id=123";

	@Test
	public void getAllStudents() {
		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json").when().get(ep_WithId)
				.then().body("Students[2].firstName", Is.is("Kola"))
				
				.log().all().extract().response();
		int status_code = response.statusCode();
		System.out.println("Status code found : " + status_code);
		Assert.assertEquals(status_code, 200);

	}

	@Test
	public void addStudent() {

		Map<String, Object> data = new HashMap<>();
		data.put("departmentsGroup", "Group:4");
		data.put("id", "5500");
		data.put("firstName", "Kola");
		data.put("lastName", "Mia");
		data.put("email", "KolaMia@zsolution.com");
		
		int newStudent = RestAssured.given()
				.contentType(ContentType.JSON)
				.accept("application/json")
				.when().post(endPoints)
				.then()
				.assertThat()
				.statusCode(HttpStatus.SC_CREATED)
				.extract()
				.path("id");
	
		
		

	}

}
