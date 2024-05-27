package com.Pages;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class User_With_Token {
    
String token;
    @Test(priority = 1)
    public void validateToken(){
        Map<String, String> data = new HashMap<>();
        data.put("email","john@mail.com");
        data.put("password","changeme");

        Response response = given().header("Content-Type", "application/json")
                .body(data).when().post("https://api.escuelajs.co/api/v1/auth/login");

        Assert.assertEquals(response.statusCode(), 201);
        //Assert.assertEquals(response.body().jsonPath().getString("message"), "User Varified successfully");
        response.prettyPrint();
        token = response.body().jsonPath().getString("access_token");
    }

    @Test(priority = 2)
    public void getUserProfile(){
    	System.out.println(token);
        Response response = given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer"+token)
                .when().get("https://api.escuelajs.co/api/v1/auth/profile")
                .then().log().all().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
      //  Assert.assertEquals(response.body().jsonPath().getString("message"), "Data received successfully");
        response.prettyPrint();
    }
}
