package com.assignment.walmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage {
	
	private WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Login to checkout with valid credentials
	 * @param username
	 * @param password
	 * @return Order details page
	 */
	public OrderFulfillmentPage loginToCheckOut(String username, String password) {
		driver.findElement(By.id("COAC0WelAccntEmail")).sendKeys(username);
		driver.findElement(By.id("COAC0WelAccntPswd")).sendKeys(password);
		driver.findElement(By.id("COAC0WelAccntSignInBtn")).click();
		return new OrderFulfillmentPage(driver);
	}
		
}
