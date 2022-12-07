package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import businessLogics.UtilityHeaders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest {

	@Test(dataProvider = "validData")
	public void performTestWithMultipleData(String authToken, String orderId) {
		RestAssured.baseURI="https://s1.noonpay.biz";
		RestAssured.basePath="p2p";
        RequestSpecification request=RestAssured.given();
		
		//Response object
		Response response = request.headers(UtilityHeaders.getCommonHeaders())
				.header("Authorization", authToken).queryParam("order_id", orderId)
				.get("v1/transactions/receiver-info");
		
		System.out.println("Response of fetch p2p is: "+response.getBody().asString());
	}
	
	//DataProvider
	@DataProvider(name="validData")
	String[][] getAuthTokenAndOrderIds() {
		String getData[][] = { { "ef71667c-984b-4999-8e22-4349e5b81b3a", "52LXJQGDD5K384X3" },
				{ "ef71667c-984b-4999-8e22-4349e5b81b3a", "2D9S16U5BKUY7TYU" } };
		return getData;
	}
}
