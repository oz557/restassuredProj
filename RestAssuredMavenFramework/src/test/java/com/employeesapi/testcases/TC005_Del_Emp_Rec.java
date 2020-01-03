package com.employeesapi.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.emplyeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC005_Del_Emp_Rec extends TestBase {
RequestSpecification httpRequest;
Response response;
@BeforeClass
void deleteEmloyee() throws InterruptedException
{
	logger.info("****started TC005_Del_Emp_Rec******");
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/";
	response=httpRequest.request(Method.GET,"/employees");
	
	//First get the JsonPath object instance from the Response interface
	JsonPath jsonPathEvaluator=response.jsonPath();
	//Capture id
	String empID=jsonPathEvaluator.get("[0].id");
	response=httpRequest.request(Method.DELETE,"/delete/"+empID); //Pass ID to delete record
	Thread.sleep(5000);
}
@Test
void checkResponseBody()
{
	String responseBody =response.getBody().asString();
	Assert.assertEquals(responseBody.contains("Successfully! deleted Rec"), true);
}
@Test
void checkStatusCode() {
	int StatusCode=response.getStatusCode();
	Assert.assertEquals(StatusCode, 200);
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
@AfterClass
void tearDown() {
	 logger.info("*****Finished TC001_Get_All_Employees********");
}
}
