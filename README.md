## walmart-cart-webdriver-test

#### Walmart Mobile App: mobile.walmart.com

###### Test Case: Add Item to cart

**Technologies Used:** *Java 7, Selenium-2.44.0, jUnit-4.10, Eclipse IDE*

**Description:** Emulate walmart's mobile website on Google Chrome Browser and test "add item to cart" functionality.

* Use Page Object Model(POM) pattern to write the tests for better maintainability and readability of the application.
* Use Junit's Parameterized class to test the flow of application with different parameters.(Here, search strings and user credentials)

**What activities does this application automate?:**

Action | Result
-------|--------
Enter item name in search field | Page that displays list of related items 
Select item that can be added to cart from the list of items| Page that displays item and "Add to cart" button 
Click "Add to cart" button| Adds item to cart and Redirects to cart page that has "View Cart" & "Checkout" buttons.
Click on "Checkout" button| Page that displays forms to sign in/create account
Enter email and password on signin form and click signin button | Redirects to checkout page
Check for the item name in checkout page and verifies if it same item that was added| -
Verify if that is only item present in the checkout page| -

*All the above actions performed for following items(tv, socks, dvd, toys, iPhone) one after other.*

**Note:** Used Google Chrome webdriver. Use the following steps to set up. 
  * Download Chrome webdriver
  * place it in the any folder inside the project
  * Set the property using below line of code.
  ```
  System.setProperty("webdriver.chrome.driver", "/path to chromedriver");
  ```
