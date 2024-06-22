package pageObjects;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class adminPage {
public static WebDriver driver;
	
	
	public adminPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//verify the adminpage
	@FindBy(xpath="//h6[.='User Management']")
	WebElement verifyadmin;
	
	public void verifyAdminpage() {
		assertTrue(verifyadmin.isDisplayed());
	}
	
	//Enter username 
	@FindBy(xpath="(//div[@class='oxd-input-group__label-wrapper'])[1]")
	WebElement username;
	
	public void enterUsername(String str) {
		username.sendKeys(str);
	}
	
	//Enter userrole as ESS
	@FindBy(xpath="//div[@class='oxd-select-text-input'][.='ESS']")
	WebElement useroleESS;
	
	public void selectESS() throws Exception {
		driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[1]")).click();
		Thread.sleep(1000);
		useroleESS.click();
	}
	
	//Select the Enabled status
	@FindBy(xpath="//div[@class='oxd-select-text-input'][.='Enabled']")
	WebElement statusEnabled;
	public void selectEnabledStatus() throws Exception {
		driver.findElement(By.xpath("(//div[@class='oxd-select-text-input'])[2]")).click();
		Thread.sleep(1000);
		statusEnabled.click();
	}
	
	//Click on search button
	@FindBy(xpath="//button[@type='submit']")
	WebElement search;
	
	public void clickSearch() {
		search.click();
	}
	
	//Click on Reset button
	@FindBy(xpath="//button[.=' Reset ']")
	WebElement resetbtn;
	
	public void clickResetbtn() {
		resetbtn.click();
	}

	//display the no. of records found
	public void displayrecordsfound() {
		
		List<WebElement> records = driver.findElements(By.xpath("//div[@class='oxd-table-card']"));
		
		//Print the no.of records found
		System.out.println("No.of records found: "+records.size());
	}
	    
}
