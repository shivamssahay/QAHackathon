package com.lambdaTestHackathon.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.ietf.jgss.Oid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lambdaTestHackathon.utilities.ExtentManager;

import com.beust.jcommander.Parameter;
import com.lambdTestHackathon.actionDriver.Action;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//	public static WebDriver driver;

	
	@BeforeTest()
	public void loadConfig() {
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			String file = System.getProperty("user.dir")+"/configFiles/config.properties";
			FileInputStream ip = new FileInputStream(file);
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver getDriver() {
		// Get Driver from threadLocalmap
		return driver.get();
	}


	public void launchApp(String browser, String url) {
		 String browserName = browser;
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new ChromeDriver());

		} 
		//Maximize the screen
		getDriver().manage().window().maximize();
		//Delete all the cookies
		getDriver().manage().deleteAllCookies();
		//Implicit TimeOuts
		getDriver().manage().timeouts().implicitlyWait
		(Integer.parseInt(prop.getProperty("implicitWait")),TimeUnit.SECONDS);
		//PageLoad TimeOuts
		getDriver().manage().timeouts().pageLoadTimeout
		(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),TimeUnit.SECONDS);
		//Launching the URL

		getDriver().get(url);

	}

	@AfterTest()
	public void afterSuite() {
		ExtentManager.endReport();
	}
	
}
