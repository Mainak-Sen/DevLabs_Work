package week5.Selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Selenium3 {
	
	public static RemoteWebDriver driver;
	public static WebDriverWait w;
	public static String inc_no,option1,option2,option3,description,updtd_desc;
	
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
		 w=new WebDriverWait(driver, 30);
		WebElement create_new =driver.findElement(By.xpath("//div[text()='Create New']"));
		w.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(create_new)));
		create_new.click();
		//w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='gsft_main']")));
		driver.switchTo().frame("gsft_main");
		try{w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='incident.number']")));}
		catch(Exception e)
		{
			driver.switchTo().defaultContent();
			create_new.click();
			driver.switchTo().frame("gsft_main");
		}
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='incident.number']")));
		 inc_no=driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		Assert.assertTrue(inc_no.startsWith("INC"));
		WebElement search=driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']"));
		search.click();
		
	
	
}
	
	@Test(dependsOnMethods={"launch_instance", "verify_INC"})
	public void inc_creation(){
	String parent_window=driver.getWindowHandle();
	WebElement search=driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']"));
	w.until(ExpectedConditions.elementToBeClickable(search));
	search.click();
	int count=0;
	while(count<=3) {
	try{w.until(ExpectedConditions.numberOfWindowsToBe(2));
	     if(driver.getWindowHandles().size()==2)
	    	 {
	    	   break;}
	    	 
	}
	catch(Exception e) {
		w.until(ExpectedConditions.elementToBeClickable(search));
		search.click();	
		count++;
	}
	}
	Set<String> handles=driver.getWindowHandles();
	List<String> handles_list=new ArrayList<String>();
	handles_list.addAll(handles);
	driver.switchTo().window(handles_list.get(1));
	driver.findElement(By.xpath(" //a[text()='Abel Tuter']")).click();
	//w.until(ExpectedConditions.numberOfWindowsToBe(1));
	driver.switchTo().window(parent_window);
	System.out.println("switched to parent window");
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='gsft_main']")));
	System.out.println("Switched to frame successfully");
	//selecting software category
	WebElement select2=driver.findElement(By.xpath("//select[@id='incident.category']"));
	w.until(ExpectedConditions.visibilityOf(select2));
	Actions builder=new Actions(driver);
	builder.moveToElement(select2).click().perform();
	Select s1=new Select(select2);
	option1="Software";
	s1.selectByVisibleText(option1);
	//Selecting operating system subcategory
	WebElement select3=driver.findElement(By.xpath("//select[@id='incident.subcategory']"));
	w.until(ExpectedConditions.visibilityOf(select3));
	Actions builder2=new Actions(driver);
	builder2.moveToElement(select3).click().perform();
	Select s2=new Select(select3);
	option2="Operating System";
	s2.selectByVisibleText(option2);
	//selecting contact type as phone 
	WebElement select4=driver.findElement(By.xpath("//select[@id='incident.contact_type']"));
	w.until(ExpectedConditions.visibilityOf(select4));
	List<WebElement> c_ops=driver.findElements(By.xpath("//select[@id='incident.contact_type']/option"));
	option3="phone";
	for(WebElement co: c_ops)
	{
		if(co.getText().equalsIgnoreCase(option3))
		{
			co.click();
		}
	}
	/*Actions builder3=new Actions(driver);
	builder3.moveToElement(select4).click().perform();
	Select s3=new Select(select4);
	s2.selectByValue(option3);*/
	description="This is an incident";
	driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys(description);
	driver.findElement(By.xpath("//button[text()='Submit']")).click();	
}
	//validation of the raised incident 
	
		@Test(dependsOnMethods={"inc_creation"})
		public void incident_validation() throws InterruptedException {
		WebElement search=driver.findElement(By.xpath("//div[@class='input-group']/input"));
		w.until(ExpectedConditions.visibilityOf(search));
		search.sendKeys(inc_no,Keys.ENTER);
		WebElement search_inc=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr/td[3]/a"));
		if(search_inc.getText().equals(inc_no)) {
			search_inc.click();
		}
		Thread.sleep(8000);
		WebElement caller=driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']"));
		
		Assert.assertTrue(caller.getAttribute("value").equalsIgnoreCase("Abel Tuter"));
		WebElement s1=driver.findElement(By.xpath("//select[@id='incident.category']"));
		WebElement s2=driver.findElement(By.xpath("//select[@id='incident.subcategory']"));
		WebElement s3=driver.findElement(By.xpath("//select[@id='incident.contact_type']"));
		WebElement desc=driver.findElement(By.xpath("//input[@id='incident.short_description']"));
		Select slct1=new Select(s1);
		Select slct2=new Select(s2);
		Select slct3=new Select(s3);
		
		Assert.assertTrue(slct1.getFirstSelectedOption().getText().equalsIgnoreCase(option1));
		Assert.assertTrue(slct2.getFirstSelectedOption().getText().equalsIgnoreCase(option2));
		Assert.assertTrue(slct3.getFirstSelectedOption().getText().equalsIgnoreCase(option3));
		Assert.assertTrue(desc.getAttribute("value").equals(description));
		System.out.println("All details persist exactly as it was filled before");
		updtd_desc="Updated Incident successfully.";
		desc.sendKeys(updtd_desc);
		driver.findElement(By.xpath("//button[@value='sysverb_update']")).click();//clicking on update button
		//Validate desc updated successfully
		Thread.sleep(10000);
		WebElement search2=driver.findElement(By.xpath("//div[@class='input-group']/input"));
		w.until(ExpectedConditions.visibilityOf(search2));
		search2.sendKeys(inc_no,Keys.ENTER);
		WebElement search_inc2=driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr/td[3]/a"));
		if(search_inc2.getText().equals(inc_no)) {
			search_inc2.click();
		}
		WebElement desc_updated=driver.findElement(By.xpath("//input[@id='incident.short_description']"));
		Assert.assertTrue(desc_updated.getAttribute("value").equals(description+updtd_desc));
		System.out.println("Updated description is reflecting as expected");
		
		}	

}	



