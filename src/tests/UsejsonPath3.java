package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsejsonPath3 {
	
//	Response is
//	    {
//		  "success": true,
//		  "data": {
//		    "summary": {
//		      "total": 43119112,
//		      "confirmedCasesIndian": 43119064,
//		      "confirmedCasesForeign": 48,
//		      "discharged": 42576815,
//		      "deaths": 524201,
//		      "confirmedButLocationUnidentified": 0
//		    },
//		    "unofficial-summary": [
//		      {
//		        "source": "covid19india.org",
//		        "total": 7945975,
//		        "recovered": 7198877,
//		        "deaths": 119538,
//		        "active": 626192
//		      }
//		    ],
//		    "regional": [
//		      {
//		        "loc": "Andaman and Nicobar Islands",
//		        "confirmedCasesIndian": 10039,
//		        "confirmedCasesForeign": 0,
//		        "discharged": 9907,
//		        "deaths": 129,
//		        "totalConfirmed": 10039
//		      },
//		      {
//		        "loc": "Andhra Pradesh",
//		        "confirmedCasesIndian": 2319797,
//		        "confirmedCasesForeign": 0,
//		        "discharged": 2304995,
//		        "deaths": 14730,
//		        "totalConfirmed": 2319797
//		      } and so on
//		      ]
//		  },
//		  "lastRefreshed": "2022-05-14T03:47:21.111Z",
//		  "lastOriginUpdate": "2022-05-14T02:30:00.000Z"
//		}

	public static void main(String[] args) {
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setBaseUri("https://api.rootnet.in");
		builder.setBasePath("covid19-in");
		builder.addHeader("Authorization", "Basic demo73836868383");
		
		RequestSpecification request=builder.build();
		
		Response response=RestAssured.given(request).relaxedHTTPSValidation().contentType(ContentType.JSON).get("stats/latest");
		
		
		JsonPath path=new JsonPath(response.asString());
		//Get the length of unofficial-summary array when there is a parent (in our case parent is data)
		System.out.println("unofficial-summary array length is:"+path.getList("data.unofficial-summary").size());//1
		
		//Get the length of regional array when there is a parent (in our case parent is data)
		System.out.println("Regional array length is:"+path.getList("data.regional").size());//36
		
		//Get confirmedCasesIndian if loc is Uttar Pradesh
		int sizeOfRegionalArr=path.getList("data.regional").size();
		
		for (int i=0;i<sizeOfRegionalArr;i++) {
			String dynamicIndex="["+i+"]";
			if (path.get("data.regional"+dynamicIndex+".loc").equals("Uttar Pradesh")){
				System.out.println("Confirmed Indian cases in UP:"+path.get("data.regional"+dynamicIndex+".confirmedCasesIndian").toString());
				break;
			}
		}

	}

}
