package com.assignment.walmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage {
	
	private WebDriver driver;
	
	public ItemPage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Add item to cart
	 * @return CartPage with the added item 
	 */
	public CartPage addSelectedItemToCart() {
		WebElement addToCartButtonElement = new WebDriverWait(driver,100).until(ExpectedConditions.elementToBeClickable(By.id("WMItemAddToCartBtn")));
		System.out.println("button content:" + addToCartButtonElement.getText());
		Actions action = new Actions(driver);
		action.moveToElement(addToCartButtonElement).click().perform();
		System.out.println("Add to cart clicked");
		
		/*WebElement addToCartButtonElement = driver.findElement(By.id("WMItemAddToCartBtn"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", addToCartButtonElement);*/
		
		return new CartPage(driver);
	}
	
	/**
	 * Get the title of the product added to cart
	 * @return product title/name 
	 */
	public String getCartItemTitle() {
		return driver.findElement(By.className("product-name")).getText();
	}

}
