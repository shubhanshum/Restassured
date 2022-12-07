package tests;

import java.util.List;
import java.util.Set;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsejsonPath1 {

	public static void main(String[] args) {
		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setBaseUri("https://api.rootnet.in");
		builder.setBasePath("covid19-in");
		
		RequestSpecification request=builder.build();
		
		Response response=RestAssured.given(request).relaxedHTTPSValidation().contentType(ContentType.JSON).get("stats/testing/latest");
		//System.out.println(response.asPrettyString());
		
		JsonPath path=new JsonPath(response.asString());
		System.out.println(path.getString("data.source")); //https://twitter.com/ICMRDELHI/status/1418414990487277569
		
		
	}

}
