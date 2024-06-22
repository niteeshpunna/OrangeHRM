package testCases;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.adminPage;
import pageObjects.homePage;
import pageObjects.loginPage;

public class TC_04_searchByUserRole extends BaseClass{
	
	@Test
	public void searchByUserStatus() throws Exception {
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
		
        //Click on admin 
        hp.clickAdmin();
        
        //Create an object for admin page to access
        adminPage ap = new adminPage(driver);
        
        //Select userrole as ESS
        ap.selectESS();
        
        //Click on search button
        ap.clickSearch();
        
        //Call method to display the no.of records
        ap.displayrecordsfound();
        Thread.sleep(1000); 
        
        //Click on Reset
        ap.clickResetbtn();
	}
}
	
