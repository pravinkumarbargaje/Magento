package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import abstractComponents.AbstractComponents;


public class CheckoutPage extends AbstractComponents{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-last-of-type(1)")
	WebElement selectCountry;
	
	@FindBy(css="button[data-role*='proceed-to-checkout']")
	WebElement clickOnCheckoutButton;
	
	@FindBy(css=".select[name*='country_id']")
	WebElement clickCountry;
	
	@FindBy(css="[data-title*='United States']")
	WebElement selectCountryName;
	
	@FindBy(css=".select[name*='region_id']")
	WebElement selectRegion;
	
	@FindBy(css="option[data-title*='Alaska']")
	WebElement selectRegionName;
	
	@FindBy(css="input[value='tablerate_bestway']")
	WebElement flatRateSelect;
	
	@FindBy(id = "s_method_tablerate_bestway")
	WebElement radioButton;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[2]/div[4]/ol[1]/li[2]/div[1]/div[3]/form[1]/div[3]/div[1]/button[1]")
	WebElement nextButton;
	
	@FindBy(xpath = "/html[1]/body[1]/div[2]/main[1]/div[2]/div[1]/div[2]/aside[1]/div[2]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[1]/strong[1]/span[1]")
	WebElement cartValueElement;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[2]/div[4]/ol[1]/li[1]/div[2]/form[1]/div[1]/div[3]/div[1]/input[1]")
	WebElement company;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[2]/div[4]/ol[1]/li[1]/div[2]/form[1]/div[1]/fieldset[1]/div[1]/div[1]/div[1]/input[1]")
	WebElement fillAddress;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[2]/div[4]/ol[1]/li[1]/div[2]/form[1]/div[1]/div[4]/div[1]/input[1]")
	WebElement fillCityName;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[2]/div[4]/ol[1]/li[1]/div[2]/form[1]/div[1]/div[7]/div[1]/input[1]")
	WebElement fillPostalCode;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/div[2]/div[4]/ol[1]/li[1]/div[2]/form[1]/div[1]/div[9]/div[1]/input[1]")
	WebElement fillMobileNumber;
	
	@FindBy(css = "img[alt='Loading...']")
	WebElement loadingImg;
	
	@FindBy(css = ".shipping-address-item.selected-item")
	WebElement addPresent;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waidForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		return new ConfirmationPage(driver);
	}
	
	public void clickOnCheckout()
	{
		clickOnCheckoutButton.click();
	}
	
	public WebElement presentAddress()
	{
		return addPresent;
	}
	
	public void fillAllDetain(String comp,String city ,String address, String postal, String mobileno) throws InterruptedException 
	{
		Thread.sleep(7000);
		company.sendKeys(comp);
		fillAddress.sendKeys(address);
		fillPostalCode.sendKeys(postal);
		fillMobileNumber.sendKeys(mobileno);
		selectRegionName.click();
		selectRegion.click();
		fillCityName.sendKeys(city);
	}
	
	public void flateRate() throws InterruptedException
	{
		flatRateSelect.click();
	}
	
	public void clickNextButton()
	{
		nextButton.click();
	}
	
	public String cartValue()
	{
		String	cartValueEle = cartValueElement.getText();
		System.out.println(cartValueElement.getText());
		return cartValueEle;
	}
}
