package Brands;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class All_Brand_List_GET_Request extends Base {

	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void beforeRequest() {
		
		RestAssured.baseURI="https://automationexercise.com";
		log.info("base uri is been provided");
		
		httpRequest = RestAssured.given();
		log.info("request object is been created");
		
		response = httpRequest.request(Method.GET,"/api/brandsList");
		log.info("response object is been created");
		
		String responseBodyVal = response.body().asString();
		System.out.println(responseBodyVal);
	}
	
	@Test
	public void verifyStatusCode() {
	    //validate response Code value
		int statusCodeVal = response.statusCode();
		Assert.assertEquals(statusCodeVal,200);
		log.info("status code is 200");
	}
	
	@Test
	public void verifyResponseBodyValueForRequest() {
		//validate response body value for response code
		int responseBodyValue1 = response.jsonPath().getInt("responseCode");
		Assert.assertEquals(responseBodyValue1,200);
		
		//validate response body for brand as Madame
		Map<String,String> json = response.jsonPath().getMap("brands[2]");
		Assert.assertEquals(json.get("brand"),"Madame");
	}
	
	@Test
	public void verifyResponseHeaderValue() {
		//validate header value
		String headerValue = response.header("Server");
		Assert.assertEquals(headerValue,"cloudflare");
	}

}
