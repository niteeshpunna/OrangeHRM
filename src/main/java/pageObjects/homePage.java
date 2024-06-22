package pageObjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {
public static WebDriver driver;
	
	
	public homePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//Verify the Home page
	@FindBy(xpath="//h6[.='Dashboard']")
	WebElement dashboard;
	
	public void verifyHomepage() {
		assertTrue(dashboard.isDisplayed());
	}
	
	//Click on Admin 
	@FindBy(xpath="//li[.='Admin']")
	WebElement adminbtn;
	
	public void clickAdmin() {
		adminbtn.click();
	}
}
