package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsejsonPath2 {
	
//	Response is 
//      {
//		  "success": true,
//		  "data": [
//		    {
//		      "day": "2020-03-12",
//		      "totalSamplesTested": 6500,
//		      "totalIndividualsTested": 5900,
//		      "totalPositiveCases": 78,
//		      "source": "Press_Release_ICMR_13March2020.pdf"
//		    },
//		    {
//		      "day": "2020-03-18",
//		      "totalSamplesTested": 13125,
//		      "totalIndividualsTested": 12235,
//		      "totalPositiveCases": 150,
//		      "source": "ICMR_website_update_18March_6PM_IST.pdf"
//		    },
//		    {
//		      "day": "2020-03-19",
//		      "totalSamplesTested": 14175,
//		      "totalIndividualsTested": 13285,
//		      "totalPositiveCases": 182,
//		      "source": "ICMR_website_update_19March_6PM_IST.pdf"
//		    },
//		    {
//		      "day": "2020-03-20",
//		      "totalSamplesTested": 15404,
//		      "totalIndividualsTested": 14514,
//		      "totalPositiveCases": 236,
//		      "source": "ICMR_website_update_20March_6PM_IST.pdf"
//		    },
//		    {
//		      "day": "2020-03-21",
//		      "totalSamplesTested": 16911,
//		      "totalIndividualsTested": 16021,
//		      "totalPositiveCases": 315,
//		      "source": "ICMR_website_update_21March_6PM_IST.pdf"
//		    }
//		    ],
//		  "lastRefreshed": "2021-07-23T17:20:02.538Z",
//		  "lastOriginUpdate": "2021-07-23T03:30:00.000AZ"
//		}

	public static void main(String[] args) {
		//RequestSpecBuilder is a class which is used to construct a request or requestspecification
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setBaseUri("https://api.rootnet.in");
		builder.setBasePath("covid19-in");
		
		RequestSpecification request=builder.build();
		
		Response response=RestAssured.given(request).relaxedHTTPSValidation().contentType(ContentType.JSON).get("stats/testing/history");
		JsonPath path=new JsonPath(response.asString());
		//getting the length of data array when there is no parent
		int size=path.getList("data").size();
		System.out.println(size); //492 it is correct size
		
		//get second day and totalSamplesTested in data array
		System.out.println(path.get("data.day[1]").toString());
		System.out.println(path.get("data.totalSamplesTested[1]").toString());
		
		//find totalPositiveCases when day is 2020-04-02
		for (int i=0;i<size;i++) {
			String dynamicValue="["+i+"]";
			if (path.get("data.day"+dynamicValue).equals("2020-04-05")) {
				System.out.println("Count for date 2020-04-05 is "+path.get("data.totalPositiveCases"+dynamicValue).toString());//3554
				break;
			}
		}
	}

}
