package tests;
import java.io.File;

import org.testng.annotations.Test;

import businessLogics.UtilityHeaders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PassRequestBodyUsingFileTest {

	@SuppressWarnings("unchecked")
	@Test(description="Debit API-Recharge Feature")
	public void myGetTest2() {
		
		File file=new File(System.getProperty("user.dir")+"/complexRequest.json");
		
		RestAssured.baseURI = "https://s1.noonpay.biz";
		RestAssured.basePath ="wallet";
		RequestSpecification request=RestAssured.given();
		
		//Response object
		Response response = request.headers(UtilityHeaders.getCommonHeaders())
			.headers("Authorization", "ef71667c-984b-4999-8e22-4349e5b81b3a").body(file)
			.post("customer/v1/debit?f=RECHARGE");
		// response.then().log().all();
		response.then().log().body();
	}
}
