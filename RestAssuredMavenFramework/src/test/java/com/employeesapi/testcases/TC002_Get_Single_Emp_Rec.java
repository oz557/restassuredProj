package com.employeesapi.testcases;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emplyeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_Get_Single_Emp_Rec extends TestBase {
RequestSpecification httpRequest;
Response response;
@BeforeClass
void getEmployeeData() throws InterruptedException {
	logger.info("****Started TC002_Get_Single_Emp_Rec*******");
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	httpRequest=RestAssured.given();
	response = httpRequest.request(Method.GET,"/employee/"+empID);
	Thread.sleep(5000);
}
	@Test
	void checkResponseBody()
	{
		String responseBody =response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);
	}
	@Test
	void checkStatusCode() {
		int StatusCode=response.getStatusCode();
		Assert.assertEquals(StatusCode, 200);
	}
	@Test
	void checkResponseTime()
	{
		long responseTime=response.getTime();
		Assert.assertTrue(responseTime<2000);
	}
	@Test
	void checkStatusLine() {
		String statusLine=response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	@Test
	void checkContentType() {
		String contentType= response.header("Content-Type");
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	@Test
	void checkserverType() {
		String serverType= response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	@Test
	void checkContentLength() {
		String contentlength=response.header("Content-Length");
		Assert.assertTrue(Integer.parseInt(contentlength)<1500);
	}
	
}

