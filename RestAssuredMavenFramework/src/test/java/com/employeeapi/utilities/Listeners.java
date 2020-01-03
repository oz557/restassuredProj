package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
public ExtentHtmlReporter htmlReporter;
public ExtentReports extent;
public ExtentTest test;
public void onStart(ITestContext testContext)
{
	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
	
	htmlReporter.config().setDocumentTitle("Automation Report"); //tile of report
	htmlReporter.config().setReportName("Rest API Testing Report"); //name of the report
	//htmlRporter.config().setTestViewChartLocation(ChrtLocation.TOP); // location of the chart
	htmlReporter.config().setTheme(Theme.STANDARD);
	
	extent= new ExtentReports();
	extent.attachReporter(htmlReporter);
	
	extent.setSystemInfo("Host name", "localhost");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "Oz");
	
}
public void onTestSuccess(ITestResult result)
{
	//test=extent.createTest(result.getClass().getName());
	//test.createNode(result.getName());
	test=extent.createTest(result.getName()); //create new entry in the report
	test.log(Status.PASS, "Test case Passed is"+result.getName());
}
public void onTestFailure(ITestResult result)
{
test=extent.createTest(result.getName()); //create new entry in the report

test.log(Status.SKIP, "Test Case SKipped is"+ result.getName());

}
public void onFinish(ITestContext testContext)
{
extent.flush();
}
}