package com.labdaTestHackathon.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lambdTestHackathon.actionDriver.Action;
import com.lambdaTestHackathon.base.BaseClass;

public class AlertsHomePage extends BaseClass {
	
	Action action= new Action();
	
	@FindBy(css="button[onclick='jsAlert()']")
	private WebElement JsAlert;
	
	@FindBy(css="button[onclick='jsConfirm()']")
	private WebElement JsConfirm;
	
	@FindBy(css="button[onclick='jsPrompt()']")
	private WebElement JsPrompt;
	
	@FindBy(id="result")
	private WebElement message;
	
	
	public AlertsHomePage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void getAlert() {
		action.click(getDriver(), JsAlert);
//		action.implicitWait(driver, 3000);
		action.acceptAlert(getDriver());
	}
	
	public void getAlerttoAccept()  {
		action.click(getDriver(), JsConfirm);
//		action.implicitWait(driver, 3000);
		action.acceptAlert(getDriver());
	}
	
	public void getAlerttoDismiss()  {
		action.click(getDriver(), JsConfirm);
//		action.implicitWait(driver, 3000);
		action.dismissAlert(getDriver());
	}
	
	public void getJSPrompt() {
		action.click(getDriver(), JsPrompt);
//		action.implicitWait(driver, 1000);
	}
	
	public void sendMessagetoPromptAlert(String message) {
		action.promptAlert(getDriver(), message);
	}
	
	public String getMessage() {
		return action.getText(getDriver(), message);
	}
	
	

}
