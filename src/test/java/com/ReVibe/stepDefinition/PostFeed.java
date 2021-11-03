package com.ReVibe.stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ReVibe.pom.EditProfileForm;
import com.ReVibe.pom.HomePage;
import com.ReVibe.pom.LogInPOM;
import com.ReVibe.pom.ProfilePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostFeed {
	
	private WebDriver driver;
	
	private HomePage homePage;
	private LogInPOM loginpage;
	private ProfilePage profilePage;
	
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/chromedriver.exe"); //property key and path location of the driver
		driver = new ChromeDriver();
		driver.get("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/");
		driver.manage().timeouts().implicitlyWait(1500, TimeUnit.SECONDS);
		homePage = new HomePage(driver); //creating instance of the HomePage
		loginpage = new LogInPOM(driver);
		profilePage = new ProfilePage(driver);
		
	}
	
	@After
	public void teardown() {
		driver.close();
	}
	
	
	@Given("A user has logged in with valid credentials for firstUser")
	public void a_user_has_logged_in_with_valid_credentials_for_first_user() {
	    loginpage.setUsername("test");
	    loginpage.setPassword("test");
	    loginpage.clickLogin();
	}

	@When("a user clicks the first check button")
	public void a_user_clicks_the_first_check_button() {
	   homePage.clickCheckBox();
	}

	@When("then goes to profile page")
	public void then_goes_to_profile_page() {
	    homePage.clickProfileTag();
	}

	@When("returns to the home page")
	public void returns_to_the_home_page() {
		profilePage.clickHomePage();
	}

	@Then("check will go down by one")
	public void check_will_go_down_by_one() {
		assertEquals("HOMEPAGE", driver.findElement(By.xpath("//h1[contains(text(),'HOMEPAGE')]")).getText());
	}

}
