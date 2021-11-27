package Products;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class All_Product_List_GET_Request extends Base {

	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void beforeRequest() {
		
		//create RestAssured base uri
		RestAssured.baseURI="https://automationexercise.com";
		log.info("assigned rest-assured base uri");
		
		//create request object
		httpRequest = RestAssured.given();
		log.info("request object is been created");
		
		//create response object
		response = httpRequest.request(Method.GET,"/api/productsList");
		log.info("response object is been created");
		
		String responseBodyVal = response.body().asString();
		System.out.println(responseBodyVal);
	}
	
	
	@Test
	public void verifyStatusCode() {
		//validate Status Code
		int statusCodeValue = response.statusCode();
		Assert.assertEquals(statusCodeValue,200);
		log.info("status code value for request is 200");
	}
	
	@Test
	public void verifyResponseBodyValueForRequest() {
		
		int responseBodyStatusCodeVal = 0;
		
		//validate response body responseCode:200;
		int responseBodyVal = response.jsonPath().getInt("responseCode");
		responseBodyStatusCodeVal = response.jsonPath().getInt("responseCode");
		Assert.assertEquals(responseBodyVal,responseBodyStatusCodeVal);
		
		
		//validate response body as product name = 'blue top' for request
		Map<String,String> json = response.jsonPath().getMap("products[0]");
		Assert.assertEquals(json.get("name"),"Blue Top");
	}
	
	
	@Test
	public void verifyResponseHeaderValue() {
		
		//validate header value as content-type
		String headerValue = response.header("Content-Type");
		Assert.assertEquals(headerValue,"text/html; charset=utf-8");
		
		//validate header value as server
		String headerValue2 = response.header("Server");
		Assert.assertEquals(headerValue2,"cloudflare");
	}
	
}
