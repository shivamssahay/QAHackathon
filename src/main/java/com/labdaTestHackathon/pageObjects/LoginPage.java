package com.labdaTestHackathon.pageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lambdTestHackathon.actionDriver.Action;
import com.lambdaTestHackathon.base.BaseClass;

public class LoginPage extends BaseClass {
	
	Action action= new Action();
	
	@FindBy(id="username")
	private WebElement userName;
	
	@FindBy(id="password")
	private WebElement password;

	@FindBy(xpath="//button[@type='submit']")
	private WebElement signInBtn;
	
	@FindBy(xpath="//div[@id='flash']")
	private WebElement InvalidpasswordMessage;
	
	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public HomePage login(String uname, String pswd,HomePage homePage) throws Throwable {
		action.scrollByVisibilityOfElement(getDriver(), userName);
		action.type(userName, uname);
		action.type(password, pswd);
		action.JSClick(getDriver(), signInBtn);
		Thread.sleep(2000);
		homePage=new HomePage();
		return homePage;
	}
	
	public String getLoginMessage() {
		return action.getText(getDriver(), InvalidpasswordMessage);
	}
	
}






