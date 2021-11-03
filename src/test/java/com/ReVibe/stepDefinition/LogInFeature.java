package com.ReVibe.stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ReVibe.pom.LogInPOM;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.platform.engine.Cucumber;



public class LogInFeature {
	private WebDriver driver;
	private LogInPOM loginpage;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		loginpage = new LogInPOM(driver);
	}

	@After
	public void teardown() {
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	@Given("A user is on the login page")
	public void a_user_is_on_the_login_page() {
		driver.get("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/");
		
	}
	
	@When("A user enters valid {string} and {string}")
	public void a_user_enters_valid_and(String username, String password) {
		username = "firstUser";
		password = "root";
		loginpage.setUsername(username);
		loginpage.setPassword(password);
		loginpage.clickLogin();
	}
	
	
	@Then("A user is redirected to the {string}")
	public void a_user_is_redirected_to_the(String currentUrl) {
		currentUrl = "http://revibe-bucket.s3-website.us-east-2.amazonaws.com/";
		assertEquals(currentUrl, driver.getCurrentUrl());
	}
	
//	@Given("A user is currently on the login page")
//	public void a_user_is_currently_on_the_login_page() {
//		driver.get("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/");
//	}
//	
//	@When("A user enters a invalid {string} and {string}")
//	public void a_user_enters_a_invalid_and(String username, String password) {
//		username = "first";
//		password = "root";
//	}
//	
//	@Then("A user is still on the {string}")
//	public void a_user_is_still_on_the(String string) {
//		assertEquals("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/login", driver.getCurrentUrl());
//	}
}
