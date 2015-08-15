package com.assignment.walmart;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.assignment.walmart.pages.CartPage;
import com.assignment.walmart.pages.CheckOutPage;
import com.assignment.walmart.pages.HomePage;
import com.assignment.walmart.pages.OrderFulfillmentPage;
import com.assignment.walmart.pages.ItemPage;
import com.assignment.walmart.pages.ResultsPage;


@RunWith(value = Parameterized.class)
public class WalmartCartTest {
	private String search;
	private String username;
	private String password;
	
	private static WebDriver driver;
	
	private HomePage homePage;
	
	public WalmartCartTest(String searchTerm, String username, String password) {
		this.search = searchTerm;
		this.username = username;
		this.password = password;
		
	}
	
	@Parameters
	public static Iterable<Object[]> data(){
		return Arrays.asList(new Object[] []{
				{"tv", "carttest@walmart.com", "testPassword"},
				{"socks", "carttest@walmart.com", "testPassword"},
				{"dvd", "carttest@walmart.com", "testPassword"},
				{ "toy car", "carttest@walmart.com", "testPassword"},
				{"iPhone", "carttest@walmart.com", "testPassword"}
		});
	}
	
	
	/**
	 * Emulate iphone6 device on Google Chrome dektop browser
	 * Load mobile.walmart.com page 
	 */
	@Before
	public void before() {
		System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver");
		
		Map<String, Object> mobileEmulation = new HashMap<String, Object>();
		mobileEmulation.put("deviceName", "Apple iPhone 6 Plus");

		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		
		driver = new ChromeDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://mobile.walmart.com");
		
		homePage = new HomePage(driver);
	}
	
	
	/**
	 * Perform search on items specified in pool
	 * Add Item to cart
	 * Verify if the item is in cart and is the only item
	 */
	@Test
	public void addItemToCartAndVerifyTest() {
		ResultsPage resultsPage = homePage.searchForItemToPurchase(search);
		
		ItemPage purchasableItemPage = resultsPage.selectPurchasableItem();
		String itemName = purchasableItemPage.getCartItemTitle();

	    CartPage cartPage = purchasableItemPage.addSelectedItemToCart();
		CheckOutPage checkOutPage = cartPage.clickCheckOut();
		OrderFulfillmentPage orderFulfillmentPage = checkOutPage.loginToCheckOut(username,password);
		
		Assert.assertTrue(orderFulfillmentPage.isSelectedItemInCart(itemName));
		int noOfItemsInCart = orderFulfillmentPage.getNoOfItemsInCart();
		Assert.assertEquals(1, noOfItemsInCart);
	}
	
	@After
	public void after(){
		driver.quit();
	}
}
