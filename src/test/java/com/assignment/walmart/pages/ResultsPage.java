package com.assignment.walmart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ResultsPage {
	
	private WebDriver driver;
	
	public ResultsPage(WebDriver driver) {
		 this.driver = driver;
	}
	
	/**
	 * Get products list based on the query string 
	 * @return List<WebElement>
	 */
	private List<WebElement> getResults() {
		List<WebElement> searchResults= driver.findElements(By.className("mobile-result-item"));
		return searchResults;
	}
	
	/**
	 * Select the item that can be purchased and that can be added to the cart
	 * Can't Purchase Items that are Out of stock
	 * @return Page that displays selected item to purchase 
	 */
	public ItemPage selectPurchasableItem() {
		WebElement purchasableItem = null;
		List<WebElement> searchResults = getResults();
		for(WebElement currentItem:searchResults) {
			if(!isProductOutOfStock(currentItem) && !isProductPriceHidden(currentItem)) {
				purchasableItem = currentItem;
				break;
			}
		}
		new Actions(driver).moveToElement(purchasableItem).click().perform();
		return new ItemPage(driver);
	}
	
	/**
	 * Check if the product is out of stock
	 * @param itemElement
	 * @return true or false
	 */
	private boolean isProductOutOfStock(WebElement itemElement) {
		return itemElement.findElement(By.className("product-price")).getText().contains("Out of stock");
	}
	
	/**
	 * Check if the product price is hidden
	 * @param itemElement
	 * @return true or false
	 */
	private boolean isProductPriceHidden(WebElement itemElement) {
		return itemElement.findElement(By.className("product-price")).getText().contains("See details in cart");
	}

}
