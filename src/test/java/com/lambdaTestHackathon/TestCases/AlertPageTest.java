package com.lambdaTestHackathon.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lambdaTestHackathon.base.BaseClass;
import com.labdaTestHackathon.pageObjects.AlertsHomePage;
import com.labdaTestHackathon.pageObjects.HomePage;

import com.labdaTestHackathon.pageObjects.LoginPage;
import com.lambdTestHackathon.actionDriver.Action;
import com.lambdaTestHackathon.utilities.Log;

public class AlertPageTest extends BaseClass {

	
	@Parameters({"browser","url"})
	@BeforeMethod()
	public void setup(String browser, String url) {
		launchApp(browser, url); 
	}
	
	
	@AfterMethod()
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test()
	public void AlertHandlding() throws Throwable {
		Log.startTestCase("AlertHandlding");
		Log.info("user is going to click on JS Alert");
		AlertsHomePage alertsPage= new AlertsHomePage();
		alertsPage.getAlert();

		Log.info("User is going to click on JS Confirm");

		alertsPage.getAlerttoAccept();
		Log.info(alertsPage.getMessage());

		Log.info("User is going to click on JS Dismiss");
		Thread.sleep(2000);
		alertsPage.getAlerttoDismiss();
		Log.info(alertsPage.getMessage());

		Log.info("User is going to click on JS Prompt");
		
		alertsPage.getJSPrompt();

		Log.info("User is going to enter message in prompt");
		
		String message = prop.getProperty("AlertMessage");
		
		alertsPage.sendMessagetoPromptAlert(message);

		Thread.sleep(2000);
		String actualmessage = alertsPage.getMessage();
		System.out.println(actualmessage);
		Log.info(alertsPage.getMessage());
		Assert.assertTrue(actualmessage.contains(message)); 
	    Log.info("Alert handling is Success");
	    Log.endTestCase("AlertHandling");
	} 
}
