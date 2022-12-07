package tests;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import businessLogics.UtilityHeaders;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidateResponseHeadersTest {

	@SuppressWarnings("unchecked")
	@Test
	public void myPostTest() {
		RequestSpecification request=RestAssured.given();
		
		//Request Body
		JSONObject requestBody=new JSONObject();
		requestBody.put("national_id", "1625371303");
		requestBody.put("country", "KSA");
		
		//Response object
		Response response = request.headers(UtilityHeaders.getCommonHeaders())
				.headers("Authorization", "6f8a1ad3-9a54-4a82-a4bf-0def13fcc41f", "X-NUserId", "41CREX0035A38ZTO")
				.body(requestBody.toJSONString()).post("https://s1.noonpay.biz/kyc/upload/national-id/v1/otp/send");
		System.out.println("Status code is: "+response.getStatusCode());
		
		//Printing all response headers
		List<Header> allResponseHeaders=response.headers().asList();
		System.out.println(allResponseHeaders);
		
		//Printing headers and their values one by one
		for(int i=0;i<allResponseHeaders.size()-1;i++) {
			String headerName =allResponseHeaders.get(i).getName();
			String headerValue=allResponseHeaders.get(i).getValue();
			System.out.println("Header name is: "+headerName+" and value is: "+headerValue);
			System.out.println("**********************************************");
		}
		
		//Getting value of a specified header
		String responseContentType=response.header("Content-Type");
		System.out.println("responseContentType: "+responseContentType);
	}
}
