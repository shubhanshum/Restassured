package tests;

import java.io.File;

import org.testng.annotations.Test;

import businessLogics.UtilityHeaders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FormDataInRequestBodyTest {

	@Test(description="Upload KYC documents -- 2 pictures & 1 data")
	public void sendFormDataInRequestBody() {
		
		File frontPicture=new File(System.getProperty("user.dir")+"/kycPictures/front.jpg");
		File backPicture=new File(System.getProperty("user.dir")+"/kycPictures/back.jpg");
		File kycDataFile=new File(System.getProperty("user.dir")+"/kycData.json");
		
		
		RestAssured.baseURI = "https://s1.noonpay.biz";
		RestAssured.basePath ="user";
		RequestSpecification request=RestAssured.given();
		
		//Response object
		@SuppressWarnings("unchecked")
		Response response = request.headers(UtilityHeaders.getCommonHeaders()).headers("Authorization", "9a26f274-65dc-40c5-b18b-c22250428d19")
				.contentType("multipart/form-data")
				.multiPart("front", frontPicture)
				.multiPart("back",backPicture)
				.multiPart("data",kycDataFile)
				.post("onboard/kyc/national-id/UAE");
		
		response.then().log().body();
				
	}
}
