package com.assignment.walmart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderFulfillmentPage {
	
	private WebDriver driver;
	
	public OrderFulfillmentPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	/**
	 * Verify if the name of item in the checkout page and determines if it is same as specified itemName or not
	 * @param itemName
	 * @return true when item name in the checkout pages matches the given itemName
	 */
	public boolean isSelectedItemInCart(String itemName) {
		WebElement checkoutHeaderButton = new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.className("checkout-header-total")));
		new Actions(driver).moveToElement(checkoutHeaderButton).click().perform();
		WebElement titleElement = new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(By.className("brick-product-name")));
		
		String itemTitleStringInCart = titleElement.getText();
		System.out.println("Item name in checkout page: "+ itemTitleStringInCart );
		
		return itemTitleStringInCart.contains(itemName);
	}
	
	/**
	 * Get No. of Items in cart
	 * @return
	 */
	public int getNoOfItemsInCart() {
		new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(By.className("brick-product-name")));
		List<WebElement> noOfitemsInCart = driver.findElements(By.cssSelector(".persistent-order-summary.active .js-order-item"));
		
		return noOfitemsInCart.size();
	}
}
