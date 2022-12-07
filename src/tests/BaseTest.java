package tests;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import businessLogics.UtilityHeaders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

	@Test(priority=0)
	public void myGetTest() {
		RestAssured.basePath="https://s1.noonpay.biz/user/customer/v1/whoami";
		//Request object
		RequestSpecification request=RestAssured.given();
		
		//Response object
		Response response = request.headers(UtilityHeaders.getCommonHeaders())
				.headers("Authorization", "6732cc5d-6abd-4fe8-b005-2d71f584dadd")
				.get("https://s1.noonpay.biz/user/customer/v1/whoami");
		String actResponse=response.getBody().asString();
		System.out.println(actResponse);
		System.out.println("Status code is: "+response.getStatusCode());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=1)
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
	}
	
	@Test(priority=2,description="Fetch receiver (p2p) details via txn channel ref id from txn history")
	public void myGetTest1() {
		RequestSpecification request=RestAssured.given();
		
		//Response object
		Response response = request.headers(UtilityHeaders.getCommonHeaders())
				.header("Authorization", "69ea267e-3773-444d-9de5-56d7262e4bb2").queryParam("order_id", "39GMSI7QUCMI7XDT")
				.get("https://s1.noonpay.biz/p2p/v1/transactions/receiver-info");
		//System.out.println("Status code is: "+response.getStatusCode());
		System.out.println("Response of fetch p2p is: "+response.getBody().asString());
		
		//Getting array by name
		Map<String, String> data = response.jsonPath().getMap("data");
		
		System.out.println("Data--"+data);
		System.out.println("Data size--"+data.size());
		//Getting object of an array
		System.out.println("Mobile-- "+data.get("mobile"));
		
	}
	
	
}
