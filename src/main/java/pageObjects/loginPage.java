package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

	public static WebDriver driver;
	
	
	public loginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	//Username id Locator
		@FindBy(xpath="//input[@placeholder='Username']")
		WebElement username;

	public void enterUsername(String str) {
		username.sendKeys(str);
	}
	
	//Password Locator
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;
	
	public void enterPassword(String str) {
		password.sendKeys(str);
	}
	
	//Login Button Locator
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginbtn;
	
	public void clickLogin() {
		loginbtn.click();
	}
}
