package pageobjects;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
//PageFactory
	@FindBy(id="email")
	WebElement userEmail;
	
	@FindBy(id="pass")
	WebElement passwordEle;
	
	@FindBy(css=".action.login")
	WebElement submit;

	@FindBy(linkText="Sign In")
	WebElement signInButton;
	
	@FindBy(css="div[class='panel header'] span[class='logged-in']")
	WebElement confirmMessage;

	@FindBy(xpath="(//button[@type='button'])[1]")
	WebElement clickOnProfile;
	
	@FindBy(linkText="Sign Out")
	WebElement signOutButton;
	
	@FindBy(linkText="Create an Account")
	WebElement createAnAccount;
	
	@FindBy(id="firstname")
	WebElement firstName;
	
	@FindBy(id="lastname")
	WebElement lastName;
	
	@FindBy(name="email")
	WebElement emailUser;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="password-confirmation")
	WebElement passwordConfirm;
	
	@FindBy(css=".submit")
	WebElement createAccountButton;
	
	@FindBy(id="firstname-error")
	WebElement firstnameError;
	
	@FindBy(id="lastname-error")
	WebElement lastnameError;
	
	@FindBy(id="email_address-error")
	WebElement emailError;
	
	@FindBy(id="password-error")
	WebElement passError;
	
	@FindBy(id="confirmation-error")
	WebElement passconfError;
//action method
	
	public void createAccount(String fname,String lname,String uEmail,String passwrd,String confPassword)
	{
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		emailUser.sendKeys(uEmail);
		password.sendKeys(passwrd);
		passwordConfirm.sendKeys(confPassword);
		createAccountButton.click();
	}
	
	public String errorMessageValidation()
	{
		 String firstNameErrorMessage =firstnameError.getText();
		 System.out.println("First Name Error: " + firstNameErrorMessage);
		 String lastNameErrorMessage = lastnameError.getText();
		 System.out.println("Last Name Error: " + lastNameErrorMessage);
		 String emailErrorMessage = emailError.getText();
		 System.out.println("Email Error: " + emailErrorMessage);
		 String passwordErrorMessage = passError.getText();
		 System.out.println("Password Error: " + passwordErrorMessage);
//		 String confirmPasswordErrorMessage =passconfError.getText();
//		 System.out.println("Confirm Password Error: " + confirmPasswordErrorMessage);
		 return emailErrorMessage;
	}
	public String errorMessageValidations()
	{
		 String confirmPasswordErrorMessage =passconfError.getText();
		 return confirmPasswordErrorMessage;
	}
	
	public  void loginApplication(String email,String password) 
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		
		
	}
	public void clickSubmit() throws InterruptedException
	{
		submit.click();
	}
	public String getconfirmMessage() throws InterruptedException
	{
		Thread.sleep(2000);
		return confirmMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://magento.softwaretestingboard.com/");
	}
	public void createAnAccount()
	{
		createAnAccount.click();
	}
	
	public void clickSignIn() 
	{
		signInButton.click();
		
	}
	
	public void clickSignOut()
	{
		clickOnProfile.click();
		signOutButton.click();
	}
}
