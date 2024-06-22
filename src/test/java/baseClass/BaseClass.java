package baseClass;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	public static WebDriver driver;
	
	@BeforeMethod
	public void launchBrowser() {
		
		//
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\chromedriver-win64\\chromedriver.exe");
				
		driver = new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login?lang=en");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		assertEquals(driver.getTitle(),"OrangeHRM");
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
