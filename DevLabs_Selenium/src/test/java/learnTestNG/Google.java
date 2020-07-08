package learnTestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Google {
	// to find the number of times cricket has occured in the landing page 
        
	    @Test
	    public void tc_004() {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.google.com");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.name("q")).sendKeys("cricket");
		driver.findElement(By.name("btnK")).click();
		System.out.println(driver.findElements(By.xpath("//*[contains(text(),'cricket')]")).size());
		System.out.println(driver.findElements(By.xpath("//*[contains(text(),'Cricket')]")).size());
		driver.close();
		

	}

}