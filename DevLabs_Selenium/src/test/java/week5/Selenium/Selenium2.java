package week5.Selenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Selenium2 {
	
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
	public void ui_Validation() {
		driver.findElement(By.cssSelector("#filter")).sendKeys("incident");
		WebDriverWait w=new WebDriverWait(driver, 30);
		WebElement all=driver.findElement(By.xpath("//ul[@aria-label='Modules for Application: Incident']/li[6]"));
		w.until(ExpectedConditions.elementToBeClickable(all));
		all.click();
		driver.switchTo().frame("gsft_main");
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='New']")));
		//Asserting New button is displayed 
		Assert.assertTrue(driver.findElement(By.xpath("//button[text()='New']")).isDisplayed());
		WebElement select=driver.findElement(By.xpath("//div[@class='input-group']/span/span/select"));
		select.click();
		Select s=new Select(select);
		List<WebElement> options=s.getOptions();
		for(WebElement o:options)
		{
			Assert.assertTrue(o.isDisplayed());	//asserting each and every option is displayed or not
		}
		String text=s.getFirstSelectedOption().getText();
		Assert.assertTrue(text.equalsIgnoreCase("Number"));//asserting Number option is selected or not 
		String phv=driver.findElement(By.xpath("//div[@class='input-group']/input")).getAttribute("placeholder");
		Assert.assertTrue(phv.equalsIgnoreCase("Search"));//asserting placeholder value is search
		List<WebElement> thead=driver.findElements(By.xpath("//table[@id='incident_table']/thead/tr/th"));
		List<String> head_list=new ArrayList<String>();
		String[] expected= {"search","number","opened_at","short_description","caller_id","priority","state","category","assignment_group","assigned_to","sys_updated_on","sys_updated_by"};
		List<String> expected_list=Arrays.asList(expected);
		for(WebElement t: thead)
		{
			if(t.getAttribute("name")!=null)
			{
				head_list.add(t.getAttribute("name"));
			}
		}
		//System.out.println(head_list);//printing the values of header names to show it is as expected.
		/*if(expected_list.size()==head_list.size()) {
		   for(int i=0;i<expected_list.size();i++)
		   {
			   for(int j=0;j<head_list.size();j++)
			   {
				   Assert.assertTrue(expected_list.get(i).trim().equals(head_list.get(j).trim()));
			   }
		   }*/
		System.out.println(head_list);
		System.out.println(expected_list);
		driver.close();
		}
		
		
	
	}



