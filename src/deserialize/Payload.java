package deserialize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {

	private String name;
	private String gender;
	private String email;
	private String status;
	
	public Payload(String name, String gender, String email, String status) {
		this.name=name;
		this.gender=gender;
		this.email=email;
		this.status=status;
	}
	
	//Default constructor
	Payload() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payload [name=" + name + ", gender=" + gender + ", email=" + email + ", status=" + status + "]";
	}	
}
