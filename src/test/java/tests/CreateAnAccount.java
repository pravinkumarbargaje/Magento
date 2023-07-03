package tests;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.ConfirmationPage;
import pageobjects.LandingPage;
import abstractComponents.AbstractComponents;

import TestComponents.BaseTest;

public class CreateAnAccount extends BaseTest {

	@Test(dataProvider="getData")
	public void createAccountTest(HashMap<String,String> input)  throws InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();																					//Go to given URL
		
		landingPage.createAnAccount();																		//click on Create An Account Button
		AbstractComponents abstractComponents = new AbstractComponents(driver);
		
		landingPage.createAccount(input.get("firstname"),input.get("lastname"),abstractComponents.generateRandomEmail(),input.get("pass"),input.get("cpass"));		
																											// Give input and Click on create an account
		ConfirmationPage confirmationPage =new ConfirmationPage(driver);
		String accountCreatedMessage=confirmationPage.confirmationMessage();
		abstractComponents.waidForWebElementToAppear(confirmationPage.verifyCreateAccountMessage());
		System.out.println(abstractComponents.generateRandomEmail());
		System.out.println(confirmationPage.confirmationMessage());
	
		Assert.assertTrue(confirmationPage.confirmationMessage().equalsIgnoreCase("Thank you for registering with Main Website Store."));
	}
	
	@Test(dataProvider="getData")
	public void createAccounterrorMessageValidation(HashMap<String,String> input)
	{
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();																					//Go to given URL
		
		landingPage.createAnAccount();
		AbstractComponents abstractComponents = new AbstractComponents(driver);
		landingPage.createAccount(input.get("fname"),input.get("lname"),input.get("email"),input.get("wpass"),input.get("wpassw"));	
		
		Assert.assertTrue(landingPage.errorMessageValidation().equalsIgnoreCase("Please enter a valid email address (Ex: johndoe@domain.com)."));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonDataMap(System.getProperty("user.dir")+"//src//test//java//data//CreateAcc.json");
		return new Object[][]  {{data.get(0)}};
	}
}

