package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Important1 {

	public static void main(String[] args) {
		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setBaseUri("https://data.covid19india.org");
		builder.setBasePath("v4/min");
		
		RequestSpecification request=builder.build();
		
		Response response=RestAssured.given(request).relaxedHTTPSValidation().contentType(ContentType.JSON).get("timeseries.min.json");
		
		JsonPath path=new JsonPath(response.asString());
		
		System.out.println(path.getString("AN.dates.2020-03-27.delta.confirmed"));
	}

}
