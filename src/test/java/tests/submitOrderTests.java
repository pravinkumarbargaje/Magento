package tests;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import pageobjects.ProductCatalogue;
import pageobjects.OrderPage;
import abstractComponents.AbstractComponents;
import pageobjects.CheckoutPage;
import TestComponents.BaseTest;
import data.DataReader;

public class submitOrderTests extends BaseTest {

	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.clickSignIn();
		landingPage.loginApplication(input.get("email"), input.get("password"));
		landingPage.clickSubmit();													
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		
		productCatalogue.whatsNewEle();
		productCatalogue.womenShirtSelect();
		
		productCatalogue.addProductToCart();
		
        productCatalogue.selectWatchesFromCatagory();
        
        OrderPage orderPage =new OrderPage(driver);
        orderPage.goToCartPage();
       
        AbstractComponents abstractComponent = new AbstractComponents(driver);
        abstractComponent.handleWindowsToSwitchFirstWindow();
       
        productCatalogue.moveToMenBottomsPant();
       
        productCatalogue.sortByLowestPrice();
        
        productCatalogue.addFirstMenPantToCart();
       
        orderPage.goToCartPage();
   
        orderPage.increasePantCount();
        
        orderPage.getTotalValue();
  
        CheckoutPage checkoutPage = new CheckoutPage(driver);
      
        checkoutPage.clickOnCheckout();
       
        if(checkoutPage.presentAddress().isDisplayed()) {
        	checkoutPage.flateRate();
        }else{

        checkoutPage.fillAllDetain(input.get("comp"), input.get("address"),input.get("city"),input.get("postal"), input.get("mobleNo"));
        checkoutPage.flateRate();
        }
        
        checkoutPage.clickNextButton();
        checkoutPage.cartValue();
        
        if (checkoutPage.cartValue().equals( orderPage.getTotalValue())) {
            System.out.println("Cart value is correct.");
        } else {
            System.out.println("Cart value is incorrect.");
        }
        orderPage.placeOrder();
        
        System.out.println("Thank you message: " + orderPage.getThankYouMessage());
        System.out.println("Order number: " + orderPage.getOrderNumber());  
	}

	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonDataMap(System.getProperty("user.dir")+"//src//test//java//data//ProductOrder.json");
		return new Object[][]  {{data.get(0)}};
	}
}
