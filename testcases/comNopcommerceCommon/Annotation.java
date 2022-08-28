package comNopcommerceCommon;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Annotation {
	
	@Test
	public void TC_01() {
		System.out.println("Run testcase");
	}
  
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Run before class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Run after class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Run before test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Run after test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Run before suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Run after suite");
  }

}
