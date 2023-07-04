package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;
	
	@FindBy(css = ".action.showcart")
	WebElement checkoutEle;

	@FindBy(linkText = "View and Edit Cart")
	WebElement goToCart;
	
	@FindBy(css = "input[data-cart-item-id*='MP06-34-Green'][title='Qty']")
	WebElement productQty;
	
	@FindBy(css = "strong span[class='price']")
	WebElement totalValue;
	
	@FindBy(css = ".action.update")
	WebElement updateValue;
	
	@FindBy(xpath = "//button[@title='Place Order']")
	WebElement placeOrderButton;
	

	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/h1[1]/span[1]")
	WebElement thankYouMessage;
	
	@FindBy(className = "order-number")
	WebElement orderNumber;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[3]/div[1]/div[2]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[4]/span[1]/span[1]/span[1]")
	WebElement sweatshirt;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[3]/div[1]/div[2]/form[1]/div[1]/table[1]/tbody[2]/tr[1]/td[4]/span[1]/span[1]/span[1]")
	WebElement watch;
	
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[3]/div[1]/div[2]/form[1]/div[1]/table[1]/tbody[3]/tr[1]/td[4]/span[1]/span[1]/span[1]")
	WebElement pant;
	
	@FindBy(css = "td[class='amount'] span span[class='price']")
	WebElement discount;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public  void goToCartPage() throws InterruptedException 
	{
		checkoutEle.click();
		Thread.sleep(2000);
		goToCart.click();
	}

	public void increasePantCount() throws InterruptedException
	{
		updateValue.click();
		productQty.clear();
		productQty.sendKeys("4");
		Thread.sleep(2000);
	}
	
	public int discountedTotal()
	{
		String sweatShirt=sweatshirt.getText();
		String waTch=watch.getText();
		String panT=pant.getText();
		String discounT=discount.getText();
		int sweatShirtValue = Integer.parseInt(sweatShirt);
		int waTchValue = Integer.parseInt(waTch);
		int panTValue = Integer.parseInt(panT);
		int discounTValue = Integer.parseInt(discounT);

		int discountedTotal = sweatShirtValue + waTchValue + panTValue - discounTValue;
		return discountedTotal;
	}
	
	public int totalValues()
	{
		int totalValues = Integer.parseInt(totalValue.getText());
		return totalValues;
	}
		
	public String getTotalValue() throws InterruptedException
	{
		
		waidForWebElementToAppear(totalValue);
		Thread.sleep(3000);
        String totalvalue =totalValue.getText();
		System.out.println(totalValue.getText());
		return totalvalue;
	}
	
	public void placeOrder() throws InterruptedException
	{
		Thread.sleep(1000);
		placeOrderButton.click();
	}
	
	public String getOrderNumber()
	{
		String orderNo = orderNumber.getText();
		return orderNo;
	}
	
	public String getThankYouMessage()
	{
		String thankYoumessage = thankYouMessage.getText();
		return thankYoumessage;
	}
}
