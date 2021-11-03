package com.ReVibe.stepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ReVibe.pom.LogInPOM;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogOutFeature {
	
	private WebDriver driver;
	private LogInPOM loginpage;

	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		loginpage = new LogInPOM(driver);
	}

	@After
	public void teardown() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	@Given("A user is on any {string} but login or registration")
	public void a_user_is_on_any_but_login_or_registration(String string) {
		String username = "firstUser";
		String password = "root";
		loginpage.setUsername(username);
		loginpage.setPassword(password);
		loginpage.clickLogin();
	}

	@When("A user clicks the {string} button")
	public void a_user_clicks_the_button(String string) {
		loginpage.clickLogout();
	   
	}

	@Then("A user is returned to the {string}")
	public void a_user_is_returned_to_the(String string) {
	    Assertions.assertEquals("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/login", driver.getCurrentUrl());
	}
}
