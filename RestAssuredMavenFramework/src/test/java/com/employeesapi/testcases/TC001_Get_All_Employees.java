package com.employeesapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.emplyeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;


public class TC001_Get_All_Employees extends TestBase{
@BeforeClass
void getallEmployeess() throws InterruptedException 
{
	logger.info("********Started TC001_Get_All_Employees******");
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	 httpRequest= RestAssured.given();
	 response = httpRequest.request(Method.GET,"/employees");
	 Thread.sleep(5000);
} 
	 @Test
	 void checkResponseBody()
	 {
		logger.info("*****Checking Response Body*******"); 
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		Assert.assertTrue(responseBody!=null);
	 }
	 @Test
	 void checkStatusCode() {
		 logger.info("******* Checking status code*******");
		 int statuscode = response.getStatusCode(); //getting status code
		 logger.info("Status Code is ==>"+ statuscode); //200
		 Assert.assertEquals(statuscode, 200);
	 }
	 @Test
	 void checkResponseTime() {
		 logger.info("****checking Response time****");
		 long responseTime = response.getTime(); //getting status line
		 logger.info("Response Time is ==>" + responseTime);
		 if(responseTime>2000)
			 logger.warn("Response time is greater than 2000");
		 Assert.assertTrue(responseTime<2000);
	 }
	 @Test
	 void checkstatusLine() {
		 logger.info("***** checking status line*****");
		 String statusLine = response.getStatusLine(); // getting status line
		 logger.info("statusLine is ==>" + statusLine);
		 Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		 
	 }
	 
	 @Test
	 void checkContentType() {
		 logger.info("***** checking ContentType*****");
		 String contentType = response.header("Content-Type"); // getting content type
		 logger.info("Content Type is ==>" + contentType);
		 Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		 
	 }
	 
	 @Test
	 void checkServerType() {
		logger.info("*****Checking Server type*****"); 
		String serverType = response.header("Server");
		logger.info("ServerType==>" + serverType);
		Assert.assertEquals(serverType,"nginx/1.16.0");
	 }
	 
	 @Test
	 void checkcontentEncoding() {
		 logger.info("****checking Content Encoding****");
		 String contentEncoding= response.header("Content-Encoding");
		 logger.info("contentEncoding ==>"+ contentEncoding);
		 Assert.assertEquals(contentEncoding, "gzip");
		 
	 }
	 @Test
	 void checkContentLength() {
		 logger.info("*****Checking Content Length");
		 String contentLength= response.header("Content-Length");
		 logger.info("contentLength==>"+ contentLength);
		 if(Integer.parseInt(contentLength)<100)
			 logger.warn("Content length is less than 100");
		 Assert.assertTrue(Integer.parseInt(contentLength)>100);
	 }
	 @SuppressWarnings("unused")
	@Test
	 void checkCookies(){
		 logger.info("**** Checking Cookies****");
		 String cookies=response.getCookie("PHPSESSID");
		 
		 
	 }
	 @AfterClass
	 void tearDown() {
		 logger.info("*****Finished TC001_Get_All_Employees********");
	 }
}

