package pageobjects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import abstractComponents.AbstractComponents;


public class ProductCatalogue extends AbstractComponents{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//PageFactory
	@FindBy(css="a[title*='Phoebe']")
	WebElement womenShirt;
	
	@FindBy(css=".swatch-option[aria-label='M']")
	WebElement shirtSize;
	
	@FindBy(css=".swatch-option[aria-label='Purple']")
	WebElement shirtColour;
	
	@FindBy(id="product-addtocart-button")
	WebElement addToCartButton;
	
	@FindBy(linkText="Gear")
	WebElement categoryGear;
	
	@FindBy(linkText="Watches")
	WebElement selectWatches;
	
	@FindBy(css="div[id='narrow-by-list'] div:nth-child(2) div:nth-child(1)")
	WebElement selectWatchPrice;
	
	@FindBy(css="a[href*='40-50']")
	WebElement selectWatchPriceRange;
	
	@FindBy(css="div[id='narrow-by-list'] div:nth-child(3) div:nth-child(1)")
	WebElement selectMaterial;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/main[1]/div[3]/div[2]/div[1]/div[2]/div[3]/div[3]/div[2]/ol[1]/li[3]/a[1]")
	WebElement selectRubber;
	
	@FindBy(linkText="Aim Analog Watch")
	WebElement selectFirstWatch;
	
	@FindBy(css="li[class*='level0 nav-3']")
	WebElement dropdownElement;
	
	@FindBy(css="a[id='ui-id-18'] span:nth-child(1)")
	WebElement dropdownElement1;
	
	@FindBy(xpath="//a[@id='ui-id-23']")
	WebElement dropdownElement2;
   
     @FindBy(className="sorter-options")
 	 WebElement sortButton;
	
     @FindBy(css=".product-item-info")
  	 WebElement firstPant;
	
     @FindBy(css="div[option-label*='34']")
 	 WebElement pantSize;
     
     @FindBy(css="div[option-label*='Green']")
 	 WebElement pantColour;
     
     @FindBy(css=".level0.nav-1")
 	 WebElement whatsNew;
     
    public void whatsNewEle()
     {
    	 whatsNew.click();
     }
     
	public WebElement womenShirtSelect()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 1750)");
		womenShirt.click();
		return womenShirt;
	}
	
	public  void addProductToCart() 
	{
		shirtSize.click();
		shirtColour.click();
		addToCartButton.click();
	}
	
	public void selectWatchesFromCatagory() throws InterruptedException
	{
		
			Actions actions = new Actions(driver);
	        actions.moveToElement(driver.findElement(By.linkText("Gear"))).build().perform();
	        actions.keyDown(Keys.CONTROL).click(driver.findElement(By.linkText("Watches"))).keyUp(Keys.CONTROL).build().perform();

	        Set<String> windows = driver.getWindowHandles();
	        String parentWindowId = driver.getWindowHandle();
	        String childWindowId = null;

	        for (String window : windows) {
	            if (!window.equals(parentWindowId)) {
	                childWindowId = window;
	                break;
	            }
	        }

	        if (childWindowId != null) {
	            driver.switchTo().window(childWindowId);
	        }
	   
	      Thread.sleep(2000);
	      selectWatchPrice.click();
	      selectWatchPriceRange.click();
	      
	      selectMaterial.click();
	      Thread.sleep(2000);
	      selectRubber.click();
	      selectFirstWatch.click();
	      addToCartButton.click();
	}
	
	public void moveToMenBottomsPant()
	{
		  Actions act = new Actions(driver);
	        act.moveToElement(dropdownElement).
	        moveToElement(dropdownElement1).
	        moveToElement(dropdownElement2).click().perform();   
	}
	
	public void sortByLowestPrice() throws InterruptedException
	{
		 
        Select dropdown = new Select(sortButton);
        
        dropdown.selectByVisibleText("Price"); 
	}
	
	public void addFirstMenPantToCart() throws InterruptedException
	{
		Thread.sleep(2000);
		firstPant.click();
		pantSize.click();
		pantColour.click();
		addToCartButton.click();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//report//"+testCaseName+".png");
		Files.copy(source.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//report//"+testCaseName+".png";
	}
}
