package com.ReVibe.stepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ReVibe.pom.HomePage;
import com.ReVibe.pom.LogInPOM;
import com.ReVibe.pom.RevibeRegistrationPage;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationFeature {
	private WebDriver driver;
	private LogInPOM loginpage;
	private HomePage homePage;
	private RevibeRegistrationPage registrationPage;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		loginpage = new LogInPOM(driver);
		homePage = new HomePage(driver);
		registrationPage = new RevibeRegistrationPage(driver);
	}
	

	
	
	@Given("The user is on the registration page")
	public void the_user_is_on_the_registration_page() {
		loginpage.clickCreateNewAccount();
	}

	@When("the user inputs their name")
	public void the_user_inputs_their_name() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		registrationPage.setName("TestUser2");
	}

	@When("the user inputs a username")
	public void the_user_inputs_a_username() {
		registrationPage.setUsername("TestUsername2");
	}

	@When("the user inputs a password")
	public void the_user_inputs_a_password() {
		registrationPage.setPassword("test");
	}

	@When("the user inputs an email")
	public void the_user_inputs_an_email() {
		registrationPage.setEmail("TestUser@email.com");
	}

	@When("the user clicks the submit button")
	public void the_user_clicks_the_submit_button() {
		registrationPage.clickSubmit();
	}
	
	@Then("user redirected back to login")
	public void user_redirected_back_to_login() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assertions.assertEquals("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/login", driver.getCurrentUrl());
	}
}
