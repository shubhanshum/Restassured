package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddResponse {

	private String id;
	private String token;
	
	public String getId() {
		return id;
	}
	public String getToken() {
		return token;
	}
	
	
}
