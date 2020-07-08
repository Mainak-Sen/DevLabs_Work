package StepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition {
	private static RemoteWebDriver driver;
	private static String first_title;
	
	@Given("browser is selected")
	public void browser_is_selected() {
		System.setProperty("webdriver.chrome.driver","./Drivers/drivers/chromedriver.exe");
        
    }

	@When("Corresponding driver initialised and timeout is set")
	public void corresponding_driver_initialised_and_timeout_is_set() {
    	driver= new ChromeDriver();  
    	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    @Then("^Browser is invoked  and it navigates to the given URL$")
    public void browser_is_invoked_and_it_navigates_to_the_given_url() {
    	driver.get("http://newtours.demoaut.com/mercurysignon.php");
    }
	@Given("User is on login page")
	public void user_is_on_login_page() {
		first_title=driver.getTitle();
	}

	@When("User logs in into application with {string} and {string}")
	public void user_logs_in_into_application_with_and(String user_name, String password) {
	driver.findElement(By.name("userName")).sendKeys(user_name);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("login")).click();
	    
	}

	@Then("It logs in into application and page title is changed")
	public void it_logs_in_into_application_and_page_title_is_changed() {
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		System.out.println("Waiting for page to appear");
	}
	Assert.assertFalse(first_title.equals(driver.getTitle()));
	System.out.println("Successfully navigated to home page after log in ");
	driver.quit();
	}

}
