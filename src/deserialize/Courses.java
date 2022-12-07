package deserialize;

import java.util.List;

public class Courses {

	private List<WebAutomation> webAutomation;
	private List<ApiAutomation> apiAutomation;
	
	public Courses(List webAutomation, List apiAutomation) {
		this.webAutomation=webAutomation;
		this.apiAutomation=apiAutomation;
	}
	
	public static class WebAutomation{
		private String courseTitle;
		private String price;
		
		public WebAutomation(String courseTitle,String price) {
			this.courseTitle=courseTitle;
			this.price=price;
		}
		
		public String getCourseTitle() {
			return courseTitle;
		}
		public void setCourseTitle(String courseTitle) {
			this.courseTitle = courseTitle;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
	}
	
	public static class ApiAutomation{

		private String courseTitle;
		private String price;
		
		public ApiAutomation(String courseTitle,String price) {
			this.courseTitle=courseTitle;
			this.price=price;
		}
		
		public String getCourseTitle() {
			return courseTitle;
		}
		public void setCourseTitle(String courseTitle) {
			this.courseTitle = courseTitle;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
	}
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<ApiAutomation> getApiAutomation() {
		return apiAutomation;
	}
	public void setApiAutomation(List<ApiAutomation> apiAutomation) {
		this.apiAutomation = apiAutomation;
	}
}
