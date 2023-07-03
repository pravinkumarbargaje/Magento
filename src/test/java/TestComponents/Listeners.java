package TestComponents;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		String filePath = null;
		try {
			
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());//
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		String filePath = null;
		try {
			
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	}

	@Override
	public void onTestSkipped(ITestResult result) {		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {		
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();	
	}
}
