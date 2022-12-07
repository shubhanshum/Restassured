package tests;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.testng.annotations.Test;

import businessLogics.UtilityHeaders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ComplexRequestBodiesTest {
	
	String transactionId="";

	@SuppressWarnings("unchecked")
	@Test(priority=1,description="Debit API-Recharge Feature")
	public void bodyContainingMultipleObjects() {
		
		RestAssured.baseURI = "https://s1.noonpay.biz";
		RestAssured.basePath ="wallet";
		RequestSpecification request=RestAssured.given();
		
		//Request Body
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> meta = new HashMap<>();
		Map<String, Object> txnDetailMeta = new HashMap<>();
		Map<String, Object> additionalFields = new HashMap<>();
		
		map.put("amount", "10.00");
		map.put("channel_id", "9");
		map.put("currency", "AED");
		map.put("txn_ref_id", "abcd");
		map.put("user2_id", "BPS_UAEX_ID");
		map.put("user2_name", "UAE Exchange");
		map.put("campaign_txn_ref_id", "");
		
		map.put("meta", meta);
		
		meta.put("txn_detl_meta", txnDetailMeta);
		
		txnDetailMeta.put("biller_name", "Du Postpaid");
		txnDetailMeta.put("category_id", "4");
		txnDetailMeta.put("logo_url", "https://assets.noonpay.biz/biller/images/dupost.png");
		txnDetailMeta.put("payment_status", "PENDING");
		txnDetailMeta.put("biller_id", "10");
		txnDetailMeta.put("customer_account_number", "588500672");
		
		txnDetailMeta.put("additional_fields", additionalFields);
		
		additionalFields.put("self_payment", true);
		additionalFields.put("service_type", "du_mob_post");
		additionalFields.put("payable_amount", "AED 51.13");
		additionalFields.put("convenience_fee", "AED 2.00");
		additionalFields.put("convenience_fee_tax", "AED 0.10");
		
		//Printing request body
		request.log().body();
		
		//Response object
		Response response = request.headers(UtilityHeaders.getCommonHeaders())
				.headers("Authorization", "9a26f274-65dc-40c5-b18b-c22250428d19").body(map)
				.post("customer/v1/debit?f=RECHARGE");
		
		response.then().log().body();
		
		//Fetching txn_id
		Map<String, String> data = response.jsonPath().getMap("data");
		transactionId=data.get("debit_txn_id");
		
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=2,description="Refund API")
	public void bodyContainingArrayAndObjects() {
		System.out.println("============================");
		System.out.println("transactionId is: "+transactionId);
		
		RestAssured.baseURI = "https://s1.noonpay.biz";
		RestAssured.basePath ="wallet";
		RequestSpecification request=RestAssured.given();
		
		//Request Body
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> metaMap = new HashMap<>();
		JSONArray locationArray = new JSONArray();
		
		map.put("refund_amount", "3.00");
		map.put("reason", "REASON");
		map.put("currency", "AED");
		map.put("txn_id", transactionId);
		map.put("txn_channel", "BILL_PAYMENTS");
		
		map.put("meta", metaMap);
		
		metaMap.put("location", locationArray);
		locationArray.add("123.2323");
		locationArray.add("34.44353");
		
		metaMap.put("device", "android");
		metaMap.put("merchant_wallet_txn_id", "4U0DEOXR44AXEHT2");
		
		//Printing request body
		request.log().body();
		
		//Response object
		Response response = request.headers(UtilityHeaders.getCommonHeaders())
				.headers("Authorization", "9a26f274-65dc-40c5-b18b-c22250428d19").body(map)
				.post("customer/v1/refund?f=9");
		
		response.then().log().body();
		
		
	}
}
