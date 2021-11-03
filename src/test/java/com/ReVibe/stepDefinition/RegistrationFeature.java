package com.ReVibe.stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ReVibe.pom.HomePage;
import com.ReVibe.pom.LogInPOM;
import com.ReVibe.pom.RevibeRegistrationPage;

import io.cucumber.java.After;
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
		driver.get("http://revibe-bucket.s3-website.us-east-2.amazonaws.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		loginpage = new LogInPOM(driver);
		homePage = new HomePage(driver);
		registrationPage = new RevibeRegistrationPage(driver);
	}
	
	@After
	public void teardown() {
		driver.close();
	}
	
	//scenario 1

		@Given("A user is visiting the the login page")
		public void a_user_is_visiting_the_the_login_page() {
		    
		}


		@When("A user clicks on the {string} button")
		public void a_user_clicks_on_the_button(String string) {
		loginpage.clickCreateNewAccount();
		}
		@Then("The user is redirected to the {string}")
		public void the_user_is_redirected_to_the(String string) {
			assertEquals("Create An Account", driver.findElement(By.xpath("//h1[contains(text(),'Create an Account')]")).getText());
		}
	
	
	
	//scenario 2
			@Given("A user is on the registration page")
			public void a_user_is_on_the_registration_page() {
			    loginpage.clickCreateNewAccount();
			}


			@When("a user enter a valid {string}, {string}, {string}, {string} and {string}")
			public void a_user_enter_a_valid_and(String name, String username, String password, String email, String profilepic) {
				name="name";
				username="username";
				password="password";
				email="email";
				profilepic="profilepic";
				registrationPage.setName(name);
				registrationPage.setUsername(username);
				registrationPage.setPassword(password);
				registrationPage.setEmail(email);
				registrationPage.setPic(profilepic);
			}
			@When("a user clicks {string}")
			public void a_user_clicks(String string) {
			  registrationPage.clickSubmit();
			}
			@Then("a user will be rerouted to the {string}")
			public void a_user_will_be_rerouted_to_the(String string) {
				assertEquals("WELCOME", driver.findElement(By.xpath("//h1[contains(text(),'WELCOME')]")).getText());

			}




}
