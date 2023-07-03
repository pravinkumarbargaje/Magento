package tests;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.ProductCatalogue;
import pageobjects.ConfirmationPage;
import pageobjects.LandingPage;
import TestComponents.BaseTest;
import abstractComponents.AbstractComponents;

public class SigningIn extends BaseTest {

	@Test(dataProvider="getData")
	public void signIn(HashMap<String,String> input) throws InterruptedException, IOException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.clickSignIn();
		landingPage.loginApplication(input.get("email"), input.get("password"));
		landingPage.clickSubmit();

		String messageLogin =landingPage.getconfirmMessage();
		
		Assert.assertTrue(messageLogin.equalsIgnoreCase("Welcome, Prakash Patil!"));
		
		landingPage.clickSignOut();
		
		ConfirmationPage confirmationPage =new ConfirmationPage(driver);
		confirmationPage.verifysignOutConfirmationMessage();
		
		String logoutConfirmMessage =confirmationPage.verifysignOutConfirmationMessage();
		
		Assert.assertTrue(logoutConfirmMessage.equalsIgnoreCase("You are signed out"));

		landingPage.clickSignIn();
		AbstractComponents abstractComponents = new AbstractComponents(driver);
		
		landingPage.loginApplication(abstractComponents.generateRandomEmail(), input.get("fpassword"));
		landingPage.clickSubmit();

		String logInFailedErrorMessag = confirmationPage.verifyFailedSignIntErrorMessage();

		Assert.assertTrue(confirmationPage.verifyFailedSignIntErrorMessage().equalsIgnoreCase("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."));
	}

	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonDataMap(System.getProperty("user.dir")+"//src//test//java//data//SignIn.json");
		return new Object[][]  {{data.get(0)}};
	}
}
