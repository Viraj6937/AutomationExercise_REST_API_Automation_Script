package Products;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class All_Product_List_POST_Request extends Base {

	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void beforeRequest() {
		
		RestAssured.baseURI="https://automationexercise.com";
		
		httpRequest = RestAssured.given();
		
		JSONObject requestparam = new JSONObject();
		
		requestparam.put("name","Mens Jeans");
		requestparam.put("price",500);
		requestparam.put("brand","Polo");
		
		httpRequest.body(requestparam.toJSONString());
		
		response = httpRequest.request(Method.POST,"/api/productsList");
		
		String responseBodyValue = response.body().asString();
		System.out.println(responseBodyValue);
	}
	
	@Test
	public void verifyStatusCode() {
		//validate status code
		int statusCodeVal = response.getStatusCode();
		Assert.assertEquals(statusCodeVal,200);
	}
	
	@Test
	public void verifyResponseBodyValueForRequest() {
		
		//validate response body value of responseCode
        int responseCodeValue = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(responseCodeValue,405);
        
        //validate response body value of message
         String responseBodyValue2 = response.jsonPath().get("message");
         Assert.assertEquals(responseBodyValue2,"This request method is not supported.");
	}
	
	@Test
	public void verifyResponseHeaderValue() {
		//validate response header value
		String headerValue = response.header("Content-Type");
		Assert.assertEquals(headerValue,"text/html; charset=utf-8");
	}

}
