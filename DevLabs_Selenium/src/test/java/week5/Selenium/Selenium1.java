package week5.Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Selenium1 {
RemoteWebDriver driver;
@Test
public void launch_instance(){

	 System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
	 driver= new ChromeDriver();
	 driver.get("https://dev53603.service-now.com/");
	 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	 driver.switchTo().frame("gsft_main");
	 driver.findElement(By.id("user_name")).sendKeys("admin");
	 driver.findElement(By.id("user_password")).sendKeys("MaaServiceNow@1");
	 driver.findElement(By.xpath("//button[text()='Login']")).click();	 
}

@Test
public void verify_INC() {
	driver.findElement(By.cssSelector("#filter")).sendKeys("incident");
	WebDriverWait w=new WebDriverWait(driver, 30);
	WebElement create_new =driver.findElement(By.xpath("//div[text()='Create New']"));
	w.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(create_new)));
	create_new.click();
	//w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='gsft_main']")));
	driver.switchTo().frame("gsft_main");
	w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='incident.number']")));
	String inc_no=driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
	Assert.assertTrue(inc_no.startsWith("INC"));
	driver.quit();
}


}
