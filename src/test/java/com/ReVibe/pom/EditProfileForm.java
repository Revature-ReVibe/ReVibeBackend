package com.ReVibe.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProfileForm {
	public EditProfileForm(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="name")
	private WebElement NameBox;
	
	public void enterNameBox() {
		this.NameBox.sendKeys("test name");
	}
	
	@FindBy(id="profilePic")
	private WebElement ProfilePicBox;
	
	public void enterProfilePic() {
		this.ProfilePicBox.sendKeys("https://mefunny-test-bucket.s3.amazonaws.com/1634357235922_orly.jpg");
	}

	@FindBy(id="username")
	private WebElement UsernameBox;
	
	public void enterUsernameBox() {
		this.UsernameBox.sendKeys("test username");
	}
	
	@FindBy(id="password")
	private WebElement PasswordBox;
	
	public void enterPasswordBox() {
		this.PasswordBox.sendKeys("testpassword");
	}
	
	@FindBy(xpath="/html[1]/body[1]/app-root[1]/app-general-form[1]/div[1]/form[1]/div[1]/button[1]/span[1]")
	private WebElement SubmitButton;
	
	public void clickSubmitButton() {
		this.SubmitButton.click();
	}
	
	
}
