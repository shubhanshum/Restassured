package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Important1 {

	public static void main(String[] args) {
		
		RequestSpecification request=RestAssured.given();
		
		Response response=request.get("https://api.covidtracking.com/v1/us/daily.json");
		System.out.println(response.then().log().body());
		
		//get the size of all elements
		int elementCount= response.jsonPath().getList("$").size();
		//print 15th  element
		System.out.println(response.body().jsonPath().getList("$").get(15).toString());
		
		
		//print 15th reponse hospitalizedCurrently
		System.out.println(response.jsonPath().get("hospitalizedCurrently[15]").toString());
		
	}

}
