package week5.Selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {
  @Test
  public void f() {
	  System.out.println("Its the main test");
  }
  @BeforeMethod
  public void beforeMethod2() {
	  System.out.println("Its beforeMethod2");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Its beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Its afterMethod");
  }
  
  @Test
  public void f2() {
	  System.out.println("Its the main test2");
  }
  @BeforeClass
  public void beforeClass2() {
	  System.out.println("Its BeforeClass2");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Its BeforeClass");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Its AfterClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Its beforeTest");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Its afterTest");
  }
  @BeforeSuite
  public void beforeSuite2() {
	  System.out.println("Its beforSuite2");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Its beforSuite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Its AfterSuite");
  }
  @AfterSuite
  public void afterSuite2() {
	  System.out.println("Its AfterSuite2");
  }

}
