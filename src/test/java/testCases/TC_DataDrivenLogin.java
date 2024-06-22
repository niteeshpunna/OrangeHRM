package testCases;

import static org.testng.Assert.assertEquals;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TC_DataDrivenLogin {
	WebDriver d;
	String excelpath = "F:\\StarAgile PAP Program\\hrm\\testdata.xlsx";
	XSSFWorkbook wb;
	XSSFSheet s;
	
	@BeforeMethod
	public void setUpBrowser()
	{
		//
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\chromedriver-win64\\chromedriver.exe");
				
		//Launch Browser
		d=new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		d.manage().deleteAllCookies();
		d.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
		
		//load web page
		d.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login?lang=en");
		
		//verify web page
		assertEquals(d.getTitle(),"OrangeHRM");
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		//close the browser
		d.quit();
	}
	@Test
	public void testLoginPage() throws Exception
	{
		
		// read data from Excel
			FileInputStream fis= new FileInputStream(excelpath);
			wb= new XSSFWorkbook(fis);//get the workbook
			s= wb.getSheet("Sheet1");
			//Iterating a rows we use loop
			for(int i=1;i<=s.getLastRowNum();i++)
			{
				
				//Enter username
				d.findElement(By.xpath("//input[@placeholder='Username']")).clear();
				d.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(s.getRow(i).getCell(1).getStringCellValue());
				String uname=d.findElement(By.xpath("//input[@placeholder='Username']")).getAttribute("value");
				//Enter Password
				d.findElement(By.xpath("//input[@placeholder='Password']")).clear();
				d.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(s.getRow(i).getCell(2).getStringCellValue());
				String password=d.findElement(By.xpath("//input[@placeholder='Password']")).getAttribute("value");
				//Click on submit button
				d.findElement(By.xpath("//button[@type='submit']")).click();
				//Thread.sleep(4000);
				
				//Case-1: Blank username & Password
				if(uname.equals("") && password.equals(""))
				{
					assertEquals(d.findElement(By.xpath("(//span)[1]")).getText(),"Required");
					assertEquals(d.findElement(By.xpath("(//span)[2]")).getText(),"Required");
					//status if Fail
					setCellData(i,3,"Invalid",excelpath);
				}
				//Case-2: Blank username & Valid/Invalid Password(no blank)
				else if(uname.equals("") && !(password.equals("")))
					{
					assertEquals(d.findElement(By.xpath("(//span)[1]")).getText(),"Required");
					setCellData(i,3,"Invalid",excelpath);
					}
				//Case-3: Valid/Invalid uname(no blank) & Blank password
				else if (!(uname.equals("")) && password.equals(""))
				{
					assertEquals(d.findElement(By.xpath("(//span)[1]")).getText(),"Required");
					setCellData(i,3,"Invalid",excelpath);
				}
				//Case-4: Valid Username & Valid Password
				//created customized method
				else if(isElementPresent(d,By.xpath("//h6[.='Dashboard']")))
						{
							d.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
							Thread.sleep(1000);
							d.findElement(By.xpath("//a[.='Logout']")).click();
							Thread.sleep(2000);
							setCellData(i,3,"Valid",excelpath);
						}
						// Both are invalid/anyone is invalid
						else
						{
							assertEquals(d.findElement(By.xpath("//p[.='Invalid credentials']")).getText(),"Invalid credentials");
							setCellData(i,3,"Invalid",excelpath);
						}
					Thread.sleep(2000);
				}
	}
			// create a reusable method for writing 
			public void setCellData(int rowindex, int colindex, String result, String excelpath) throws Exception
			{
				s.getRow(rowindex).createCell(colindex).setCellValue(result);
				FileOutputStream fos = new FileOutputStream(excelpath);
				wb.write(fos);
			}
			
				public boolean isElementPresent(WebDriver driver, By locator)
			{
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
				try
				{
					driver.findElement(locator);
					return true;
				}
				catch(NoSuchElementException e)
				{
					return false;
				}		    
			}
}