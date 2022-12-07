package deserialize;

import org.codehaus.jackson.type.TypeReference;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiCall {
	
	public static void main(String[] args) throws JsonProcessingException {
		Payload p=new Payload("Shubhanshu Mishra","Male","shu44@gmail.com","Active");
		
		RestAssured.baseURI="https://gorest.co.in";
		RestAssured.basePath="public-api";
		RequestSpecification request=RestAssured.given();
		
		//Searialization
		Response response=request.headers("Authorization",
				"Bearer fab6fd9e3ae42ab83ead187a5558b23b2f7e6a56750580aa74153af443e4ed37","Content-Type","application/json",
				"Accept","application/json").contentType(ContentType.JSON).body(p).post("users");
		System.out.println(response.getBody().asString());
		
		//Deserialization
		
		Payload pay=response.getBody().as(deserialize.Payload.class);
		System.out.println(pay.toString());
	}
	
}
