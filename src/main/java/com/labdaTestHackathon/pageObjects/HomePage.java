package com.labdaTestHackathon.pageObjects;

	
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	import com.lambdTestHackathon.actionDriver.Action;
	import com.lambdaTestHackathon.base.BaseClass;

	public class HomePage extends BaseClass {
		
		Action action= new Action();
		
		@FindBy(xpath="//a[@class='button secondary radius']")
		private WebElement logOutCTA;
		
		public HomePage() {
			PageFactory.initElements(getDriver(), this);
		}
		
		public String getCurrURL() throws Throwable {
			String homePageURL=action.getCurrentURL(getDriver());
			return homePageURL;
		}
		
		public void logout(String uname, String pswd,HomePage homePage) throws Throwable {
			action.click(getDriver(), logOutCTA);
			Thread.sleep(2000);
		}
}

