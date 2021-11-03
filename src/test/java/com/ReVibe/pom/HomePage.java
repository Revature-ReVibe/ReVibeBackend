package com.ReVibe.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="profile")
	private WebElement ProfileTag;
	
	public void clickProfileTag() {
		this.ProfileTag.click();
	}
	
	@FindBy(xpath="//mat-icon[contains(text(),'add')]")
	private WebElement AddVibeButton;
	
	public void clickAddVibeButton() {
		this.AddVibeButton.click();
	}
	
	@FindBy(id="mat-input-6")
	private WebElement WhatsUpBox;
	
	public void enterWhatsUpBox() {
		this.WhatsUpBox.sendKeys("Test");
	}
	
	@FindBy(id="mat-input-7")
	private WebElement ImageUrlBox;
	
	public void enterImageUrl() {
		this.ImageUrlBox.sendKeys("https://mefunny-test-bucket.s3.amazonaws.com/1634357235922_orly.jpg");
	}
	
	@FindBy(xpath="//mat-icon[contains(text(),'insert_photo')]")
	private WebElement InsertImageButton;
	
	public void clickInsertImageButton() {
		this.InsertImageButton.click();
	}
	
	
	@FindBy(xpath="//span[contains(text(),'Submit')]")
	private WebElement SubmitVibeButton;
	
	public void clickSubmitVibeButton() {
		this.SubmitVibeButton.click();
	}
	
	@FindBy(xpath="//mat-icon[contains(text(),'close')]")
	private WebElement CloseNewVibeButton;
	
	public void clickCloseNewVibeButton() {
		this.CloseNewVibeButton.click();
	}
	
	@FindBy(id="logout")
	private WebElement LogoutTag;
	
	public void clickLogoutTag() {
		this.LogoutTag.click();
	}
}
