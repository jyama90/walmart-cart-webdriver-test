package com.assignment.walmart.pages;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	private WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Click Checkout button
	 * @return page that displays the item that was added to cart
	 */
	public CheckOutPage clickCheckOut(){
		WebElement checkOutButtonElement = (new WebDriverWait(driver,100)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='checkout']")));

		Assert.assertNotNull(checkOutButtonElement.getText());
		System.out.println(checkOutButtonElement.getText());
		Actions actions = new Actions(driver);
		actions.moveToElement(checkOutButtonElement).click().perform();
		
		return new CheckOutPage(driver);
	}
}
