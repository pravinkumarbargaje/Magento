package abstractComponents;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	@FindBy(css = "img[alt='Loading...']")
	WebElement loadingImg;

	public void handleWindowsToSwitchFirstWindow()
	{
		  String firstWindowHandle = driver.getWindowHandle(); // Store the handle of the first window
	        Set<String> windowHandles = driver.getWindowHandles(); // Get all open window handles

	        for (String handle : windowHandles) {
	            if (!handle.equals(firstWindowHandle)) {
	                driver.switchTo().window(handle); // Switch to the second window
	                break;
	            }
	        }
	        driver.close(); // Close the second window
	        driver.switchTo().window(firstWindowHandle); // Switch back to the first window
	        driver.navigate().refresh(); // Refresh the first window
	}
	
	public void waidForElementToAppear(By findBy)
	{
		
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waidForWebElementToAppear(WebElement findBy)
	{
		
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public OrderPage goToOrdersPage()
	{
			orderHeader.click();
			OrderPage orderPage = new OrderPage(driver);
			return orderPage;
	}
	public void waitForElementToDisappear(WebElement loadingImg) throws InterruptedException
	{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.invisibilityOf(loadingImg));
	}
 
	public String generateRandomEmail() 
	{
	        String alphabet = "abcdefghijklmnopqrstuvwxyz";
	        int length = 6;
	        Random random = new Random();
	        StringBuilder email = new StringBuilder();
	        
	        for (int i = 0; i < length; i++) {
	            int index = random.nextInt(alphabet.length());
	            char randomChar = alphabet.charAt(index);
	            email.append(randomChar);
	        	}
	        for (int i = 0; i < 3; i++) {
	            int randomNumber = random.nextInt(10);
	            email.append(randomNumber);
	        	}
	        email.append("@gmail.com");  
	        return email.toString();
	    }
	}
