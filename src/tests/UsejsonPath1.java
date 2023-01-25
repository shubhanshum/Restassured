package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsejsonPath1 {

	public static void main(String[] args) {
		
		//RequestSpecBuilder is a class in rest assured which is used to construct
		//request. You can set base uri, base path, headers, query params and body etc.
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setBaseUri("https://api.rootnet.in");
		builder.setBasePath("covid19-in");
		//builder.addHeaders(Map)
		//builder.addQueryParams(Map);
		//builder.setBody(null);
		
		//RequestSpecification is an interface which allow us to specify how the request will look like
		RequestSpecification request=builder.build();
		
		Response response=RestAssured.given(request).relaxedHTTPSValidation().contentType(ContentType.JSON).get("stats/testing/latest");
		//Getting logs as well using below statement
		//Response response1=RestAssured.given(request).log().all().relaxedHTTPSValidation().contentType(ContentType.JSON).get("stats/testing/latest");
		
		
		JsonPath path=new JsonPath(response.asString());
		System.out.println(path.getString("data.source")); //https://twitter.com/ICMRDELHI/status/1418414990487277569
		
		
	}

}
