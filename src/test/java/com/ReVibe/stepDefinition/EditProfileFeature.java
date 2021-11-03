package com.ReVibe.stepDefinition;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ReVibe.pom.EditProfileForm;
import com.ReVibe.pom.HomePage;
import com.ReVibe.pom.LogInPOM;
import com.ReVibe.pom.ProfilePage;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditProfileFeature {

private WebDriver driver;	
	
	private HomePage homePage;
	private LogInPOM loginpage;
	private ProfilePage profilePage;
	private EditProfileForm editProfileForm;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/chromedriver.exe"); //property key and path location of the driver
		driver = new ChromeDriver();
		driver.get("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		homePage = new HomePage(driver); //creating instance of the HomePage
		loginpage = new LogInPOM(driver);
		profilePage = new ProfilePage(driver);
		editProfileForm = new EditProfileForm(driver);
	}
	
	@Given("User has logged in successfully")
	public void user_has_logged_in_successfully() {
		String username = "firstUser";
		String password = "root";
		loginpage.setUsername(username);
		loginpage.setPassword(password);
		loginpage.clickLogin();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			System.out.println("took too long");
			e.printStackTrace();
		}
	}
	

//	@Given("User is on the home page")
//	public void user_is_on_the_home_page() {
//		driver.get("http://revibe-bucket.s3-website.us-east-2.amazonaws.com/");
//		
//	}

	@When("User clicks on profile tag on navigation bar")
	public void user_clicks_on_profile_tag_on_navigation_bar() {
		 homePage.clickProfileTag();
	}



	@When("User clicks on edit button")
	public void user_clicks_on_edit_button() {
	    profilePage.clickEditProfile();
	}

	
	@When("User enters new {string},{string}")
	public void user_enters_new(String name, String profilePic) {
		editProfileForm.enterNameBox();
		editProfileForm.enterProfilePic();
		
	}

	@When("User clicks submit button")
	public void user_clicks_submit_button() {
	    editProfileForm.clickSubmitButton();
	}



	@When("User clicks on profile in navigation bar")
	public void user_clicks_on_profile_in_navigation_bar() {
	    homePage.clickProfileTag();
	}



	@Then("New {string}, {string} is displayed on profile page")
	public void new_is_displayed_on_profile_page(String name, String profilePic) {
	    name = "test name";
	    profilePic = "https://mefunny-test-bucket.s3.amazonaws.com/1634357235922_orly.jpg";
	    WebElement newName = driver.findElement(By.id("name"));
	    WebElement newProfilePic = driver.findElement(By.xpath("//mat-card-header/img[1]"));
	    
	    Assertions.assertEquals(name, newName);
	    Assertions.assertEquals(newProfilePic.getAttribute("src"), profilePic);
	}

}
