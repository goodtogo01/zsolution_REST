package com.Pages;

import static org.testng.Assert.assertThrows;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Base.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Students {
	Helper hp = new Helper();
	String groupName = "Group:4";
	static Response response;
	String endPoints = "http://localhost:3000/Students/";
	String ep_WithId = "http://localhost:3000/Students/57";


	@Test
	public void getAllStudents_GET() {
		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json")
				.when().get(endPoints)
				.then()
				.assertThat()
				.statusCode(200)
				.log()
				.all()
				.extract()
				.response();
	}
	
	
	@Test
	public void getPerticulerStudents_GET() {
		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json")
				.when().get(ep_WithId)
				.then().log().all().extract().response();
		int status_code = response.statusCode();
		System.out.println("Status code found : " + status_code);
		Assert.assertEquals(status_code, 200);

	}

	@Test
	public void addStudent_POST() throws IOException, Exception {

		Map<String, Object> data = new HashMap<>();
		data.put("departmentsGroup", groupName);
		data.put("id", hp.randomId());
		data.put("firstName", hp.firstName());
		data.put("lastName", hp.lastName());
		data.put("email", hp.emails());

		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json").when().body(data)
				.post(endPoints).then().assertThat().statusCode(201).log().all().extract().response();

		int status_code = response.statusCode();
		System.out.println("Status code found : " + status_code);
		Assert.assertEquals(status_code, 201);
		
		System.out.println(response.statusCode());
		System.out.println("The Body is \n"+response.body().asPrettyString());
	}
	@Test
	public void updateStudentRecord_PUT() {

		Map<String, Object> data = new HashMap<>();
		data.put("departmentsGroup", groupName);
		data.put("firstName", "Vasti");
		data.put("lastName", "Blanda");
		data.put("email","VestaBlanda@mailinator.com");
		
		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json").when()
				.body(data).put(ep_WithId).then().log().all().extract().response();

	}
	@Test
	public void delectRecord_DELETE() {
		response = RestAssured.given().contentType(ContentType.JSON).accept("application/json").when()
				.delete(ep_WithId).then().assertThat().statusCode(200).log().all().extract().response();

	}
}
