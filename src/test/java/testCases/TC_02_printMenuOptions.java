package testCases;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.homePage;
import pageObjects.loginPage;

public class TC_02_printMenuOptions extends BaseClass{
	
	@Test
	public void printMenu() throws Exception {
		//Create an object for login page to access 
		loginPage lp =new loginPage(driver);
		
		//Enter email ID
		lp.enterUsername("Admin");
	
		//Enter Password
		lp.enterPassword("admin123");
				
		//Click on Login button
		lp.clickLogin();
		Thread.sleep(1000);
		
		//Create an object for home page to access
		homePage hp = new homePage(driver);
		
		//Verify the Home page
		hp.verifyHomepage();
		
		// Locate the menu element and count <li> tags
        WebElement menu = driver.findElement(By.xpath("//nav[@class='oxd-navbar-nav']"));
        List<WebElement> menuOptions = menu.findElements(By.tagName("li"));

        // Print the count of menu options
        System.out.println("Count of menu options are: " + menuOptions.size());

        // Ensure that there are menu options
        assertTrue(menuOptions.size() > 0);

        //Click on admin 
        hp.clickAdmin();
        
	}
}
	
