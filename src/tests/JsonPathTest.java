package tests;

import java.util.List;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import businessLogics.UtilityHeaders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonPathTest {

	@Test
	public void myGetTest() {
		RequestSpecification request=RestAssured.given();
		
		//Response object
		Response response = request.headers(UtilityHeaders.getCommonHeaders())
				.headers("Authorization", "cge-a57aac3f-79cd-4855-9826-fb2c76a54a4f")
				.get("https://s1.noonpay.biz/wallet/customer/v1/history");
		String actResponse=response.getBody().asString();
		String resData=JsonPath.read(actResponse, "$.data.transaction_history");
		System.out.println(resData);
		//System.out.println("Json Path :"+response.jsonPath().getString("data.transaction_history.2020-11-23.length"));
		
		
		
		
		
	}
}
