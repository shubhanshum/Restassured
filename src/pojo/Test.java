package pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Test {

	public static void main(String[] args) throws JsonProcessingException {
		RequestClass req=new RequestClass("eve.holt@reqres.in","pistol");
		
		//ObjectMapper class is used to create JSON object of RequestClass
		ObjectMapper objectMapper = new ObjectMapper();
		String employeeJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(req);
		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setBaseUri("https://reqres.in");
		builder.setBasePath("api");
		
		RequestSpecification request=builder.build();
		
		Response response=RestAssured.given(request).relaxedHTTPSValidation().contentType(ContentType.JSON).body(employeeJson).post("register");
		
		ResponseBody resBody=response.getBody();
		
		AddResponse addRes=resBody.as(AddResponse.class);
		
		System.out.println("Id is:"+addRes.getId());
		System.out.println("Token is:"+addRes.getToken());
		
		

	}

}
