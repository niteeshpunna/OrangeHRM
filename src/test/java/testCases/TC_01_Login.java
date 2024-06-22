package testCases;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.loginPage;

public class TC_01_Login extends BaseClass{

	@Test
	public void testLogin() {
		
		loginPage lp =new loginPage(driver);
		
		//Enter email ID
		lp.enterUsername("Admin");
		
		//Enter Password
		lp.enterPassword("admin123");
		
		//Click on Login button
		lp.clickLogin();
		
		//Verify the Home page
				
	}
}
