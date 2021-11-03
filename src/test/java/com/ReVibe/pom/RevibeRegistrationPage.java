package com.ReVibe.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RevibeRegistrationPage {

	
	public RevibeRegistrationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="registration-nameInput")
	private WebElement nameBox;
	@FindBy(id="registration-usernameInput")
	private WebElement usernameBox;
	@FindBy(id="registration-passwordInput")
	private WebElement passwordBox;
	@FindBy(id="registration-emailInput")
	private WebElement emailBox;
	@FindBy(id="registration-picInput")
	private WebElement picBox;
	@FindBy(id="new-account-submit")
	private WebElement submitButton;
	
	public void setName(String name) {
		nameBox.sendKeys(name);
	}
	
	public void setUsername(String username) {
		usernameBox.sendKeys(username);
	}
	
	public void setPassword(String password) {
		passwordBox.sendKeys(password);
	}
	
	public void setEmail(String email) {
		emailBox.sendKeys(email);
	}
	
	public void setPic(String pic) {
		picBox.sendKeys(pic);
	}
	
	public void clickSubmit() {
		submitButton.click();
	}
}
