package Brands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class All_Brand_List_PUT_Request extends Base {

	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	
	@BeforeTest
	public void beforeRequest() {
		
		RestAssured.baseURI="https://automationexercise.com";
		log.info("base uri is been created");
		
		httpRequest = RestAssured.given();
		log.info("request object is been created");
		
		response = httpRequest.request(Method.PUT,"/api/brandsList");
		log.info("response object is been created");
		
		String responseBodyVal = response.body().asString();
		System.out.println(responseBodyVal);
	}
	
	@Test
	public void verifyStatusCode() {
		int statusCodeVal = response.getStatusCode();
		Assert.assertEquals(statusCodeVal,200);
		log.info("status code for request is 200");
	}
	
	@Test
	public void verifyResponseBodyValue() {
		
		int responseBodyVal1 = response.jsonPath().getInt("responseCode");
		Assert.assertEquals(responseBodyVal1,405);
		log.info("responseCode value for request is"+responseBodyVal1);
		
		String responseBodyVal2 = response.jsonPath().get("message");
		Assert.assertEquals(responseBodyVal2,"This request method is not supported.");
		log.info("message in response body for request is"+responseBodyVal2);
	}
}
