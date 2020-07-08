package learnTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Alerts {
     
	@Test
	public void tc_001() {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","./Drivers/drivers/chromedriver.exe");
		ChromeDriver driver= new ChromeDriver();
		driver.get("\r\n" + 
				"https://play.letcode.in/frame" + 
				"");
		driver.manage().window().maximize();
		driver.switchTo().frame(0);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.switchTo().alert().accept();
		



	}

}
