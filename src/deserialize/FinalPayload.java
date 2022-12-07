package deserialize;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import deserialize.Courses.ApiAutomation;
import deserialize.Courses.WebAutomation;

public class FinalPayload {

	public static void main(String[] args) throws JsonProcessingException {
		
		List<WebAutomation> web=new ArrayList<>();
		Courses.WebAutomation sel=new Courses.WebAutomation("selenium", "2000");
		
		web.add(sel);
		
		List<ApiAutomation> api=new ArrayList<>();
		Courses.ApiAutomation rest=new Courses.ApiAutomation("rest assured", "5000");
		api.add(rest);
		
		Courses co=new Courses(web,api);
		Payload pay=new Payload("shubham", "www.sm.com", "testing", "automation", "linkedin",co);
		
		
		ObjectMapper map=new ObjectMapper();
		String response=map.writerWithDefaultPrettyPrinter().writeValueAsString(pay).toString();
		System.out.println(response);
		
		Payload py=map.readValue(response, Payload.class);
		String service=py.getServices();
		System.out.println(service);

	}

}
