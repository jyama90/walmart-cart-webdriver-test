package com.assignment.walmart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Get products list based on the query string 
	 * @param searchTerm
	 * @return Page that displays list of results
	 */
	public ResultsPage searchForItemToPurchase(String searchTerm) {
		driver.findElement(By.name("query")).sendKeys(searchTerm);
		driver.findElement(By.name("query")).submit();
		return new ResultsPage(driver);
	}
}
