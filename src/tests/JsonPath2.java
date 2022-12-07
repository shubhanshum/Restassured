package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonPath2 {

	public static void main(String[] args) {
		RestAssured.baseURI="https://gorest.co.in";
		RestAssured.basePath="public-api";
		RequestSpecification request=RestAssured.given();
		
		Response response=request.headers("Authorization",
				"Bearer fab6fd9e3ae42ab83ead187a5558b23b2f7e6a56750580aa74153af443e4ed37","Content-Type","application/json")
				.put("todos");
		
		System.out.println(response.getBody().asString());

	}

}
