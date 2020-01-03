package com.employeesapi.testcases;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.utilities.RestUtils;
import com.emplyeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Emp_Rec extends TestBase {
RequestSpecification httpRequest;
Response response;

String empName=RestUtils.empName();
String empSalary=RestUtils.empSal();
String empAge=RestUtils.empAge();

@SuppressWarnings("unchecked")
@BeforeClass
void createEmployee() throws InterruptedException
{
	logger.info("*****Started TC003_Post_Emp_Rec*****");
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	httpRequest=RestAssured.given();
	
	//JSONObject is class that represents a simple JSON. We can add key-Value pairs using the put method
	//{"employee_name":"joy890","employee_salary":"45","employee_age":"50000"}
JSONObject requestParams= new JSONObject();
requestParams.put("name", "empName");
requestParams.put("salary", "empSalary");
requestParams.put("age", "empAge");



//Add a header stating the request body is a JSON
httpRequest.header("Content-Type","application/json");

//add the json to the body of the request
httpRequest.body(requestParams.toJSONString());
response=httpRequest.request(Method.POST,"/create");
Thread.sleep(5000);
}
@Test
void checkResponseBody()
{
	String responseBody=response.getBody().asString();
	Assert.assertEquals(responseBody.contains(empName), true);
	Assert.assertEquals(responseBody.contains(empSalary), true);
	Assert.assertEquals(responseBody.contains(empAge),true);
}

@Test

	void checkStatusCode()
	{
		int statusCode= response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

@Test
void checkstatusLine() {
	String statusLine=response.getStatusLine();
	Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
}
@Test
void checkContentType()
{
String contentType=response.header("Content-Type");
Assert.assertEquals(contentType,"text/html; charset=UTF-8");
}
@Test()
	void checkserverType() {
	String serverType=response.header("Server");
	Assert.assertEquals(serverType, "nginx/1.16.0");
	}

@Test()
void checkcontentEncoding(){
	String contentEncoding=response.header("Content-Encoding");
Assert.assertEquals(contentEncoding, "gzip");
}
@AfterClass
void tearDown() {
	 logger.info("*****Finished TC001_Get_All_Employees********");
}
}












