package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;
import pageobjects.CheckoutPage;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".base")
	WebElement signOutConfirmationMessage;
	
	@FindBy(css=".page.messages")
	WebElement signOutErrorMessage;
	
	@FindBy(css=".page.messages")
	WebElement createAccountMessage;
	
	public String verifysignOutConfirmationMessage()
	{
		return signOutConfirmationMessage.getText();
	}
	
	public String verifyFailedSignIntErrorMessage() throws InterruptedException
	{
		Thread.sleep(1000);
		return signOutErrorMessage.getText();
	}
	
	public WebElement verifyCreateAccountMessage()
	{
		return createAccountMessage;
	}
	public String confirmationMessage() throws InterruptedException
	{
		Thread.sleep(1000);
		return createAccountMessage.getText();
	}
}

