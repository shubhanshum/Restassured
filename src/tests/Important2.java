package tests;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Important2 {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://yc-intermediate-in.stage.apac.yaradigitallabs.io";
		RestAssured.basePath="api/v1/user/transactions";
		RequestSpecification request=RestAssured.given();
		
		Map<String, String> requestHeaders=new HashMap<>();
		requestHeaders.put("accept", "application/json");
		requestHeaders.put("loyalty_id", "ab194e55-002a-4f5a-8920-c878f111f461");
		requestHeaders.put("app-key", "yara_india");
		requestHeaders.put("app-secret", "yaraIndia2020");
		
		Response response=request.headers(requestHeaders).queryParam("product_info", false).get();
		System.out.println(response.prettyPrint());
		
		//Getting count of result array
		System.out.println(response.jsonPath().getList("result").size()); //2
		
		//Getting values from second array, id of second array
		System.out.println(response.jsonPath().getString("result.id[1]")); //197255674
		
		//Getting promotional_split>program_name
		System.out.println(response.jsonPath().getString("result.promotional_split[0].program_name[0]")); //Yara India demoDefaultProgram
		

	};
};
