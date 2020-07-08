package learnTestNG;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class letcode {
	RemoteWebDriver driver;
	
	@Test
    @Parameters({"browser"})	
	public void tc_003(String browser_name) {
		// TODO Auto-generated method stub
		if(browser_name.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver","./Drivers/drivers/chromedriver.exe");
		 driver= new ChromeDriver();
		}
		else if(browser_name.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","./Drivers/drivers/geckodriver.exe");
			 driver= new FirefoxDriver();
		}
		driver.get("https://play.letcode.in/checkbox\r\n" + 
				"");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		List<WebElement> checkboxes=driver.findElements(By.xpath("//input[@type='checkbox']"));
		for(WebElement w:checkboxes)
		{
			if(!w.isSelected())
			{
				w.click();
			}
		}
		
		driver.close();

	}

}
