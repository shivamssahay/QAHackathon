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

import com.labdaTestHackathon.pageObjects.HomePage;

import com.labdaTestHackathon.pageObjects.LoginPage;
import com.lambdaTestHackathon.utilities.Log;

public class LoginPageTest extends BaseClass {
	private LoginPage loginPage;
	private HomePage homePage;
	
	@Parameters({"browser","url"})
	@BeforeMethod()
	public void setup(String browser, String url) {
		launchApp(browser, url); 
	}
	
	
	@AfterMethod()
	public void tearDown() {
		getDriver().quit();
	}
	@Test
	public void loginTest() throws Throwable {
		String uname = prop.getProperty("username");
	    String pswd = prop.getProperty("password");
		System.out.print("-----");
		Log.startTestCase("loginTest");
		Log.info("user is going to click on SignIn");
		loginPage = new LoginPage();
		Log.info("Enter Username and Password");
		homePage=loginPage.login(uname,pswd,homePage);
	    String actualURL=homePage.getCurrURL();
	    String expectedURL=prop.getProperty("Loginurl");
	    Log.info("Verifying if user is able to login");
	    AssertJUnit.assertEquals(actualURL, expectedURL);
	    Log.info("Login is Sucess");
	    Log.endTestCase("loginTest");
	}
	@Test()
	public void loginTestwithInvalidCredentials() throws Throwable {
		String uname = prop.getProperty("invalidUserName");
	    String pswd = prop.getProperty("invalidPassword");
		
		Log.startTestCase("login with Invalid Creds Test");
		Log.info("user is going to click on SignIn");
		
		loginPage = new LoginPage();
		Log.info("Enter Username and Password");
		
		homePage=loginPage.login(uname,pswd,homePage);
		
		
	
	    Log.info("Verifying if user is able to login");
	    boolean isInvalidcreds = loginPage.getLoginMessage().contains("invalid");
	    Assert.assertTrue(isInvalidcreds);
	    Log.info("Login is failed");
	    Log.endTestCase("login Failed Test");
	   
	}
	
    
}

