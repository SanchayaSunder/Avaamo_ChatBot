package com.qa.avaamo.testPages;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.avaamo.base.BasePage;
import com.qa.avaamo.pages.IraTestAgentPage;
import com.qa.avaamo.util.Constants;
import com.qa.avaamo.util.ElementUtil;




public class IraChatBotTest {
	
	Properties prop;
	BasePage basepage;
	public WebDriver driver;
	IraTestAgentPage TestAgentPage;
	ElementUtil util;
	
   // ExtentTest test;
	
	//BM-T-AM
		@BeforeTest
		public void setup()
		{
			basepage=new BasePage();
			prop=basepage.init_properties();	
			util=new ElementUtil(driver);
			String browser=prop.getProperty("browser");
			driver=basepage.init_driver(browser);
			String url=prop.getProperty("url");
			driver.get(url);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			TestAgentPage=new IraTestAgentPage(driver);
		}

		
		@Test(priority=1)
		public void verifyLoginPageTitle()
		{
			 String title=TestAgentPage.getPageTitle();
			 Assert.assertEquals(title,Constants.IraPageTitle);
			 System.out.println(title);
		}
		
		@Test(priority=2)
		public void verifyWelcomeMessage()
		{
			 
			boolean isDisplayed=TestAgentPage.iraNotificationPop();
			Assert.assertTrue(isDisplayed);
			String WelcomeMsg=TestAgentPage.getNotificationText();
			Assert.assertEquals(WelcomeMsg, Constants.WelcomeMessage);
						 
		}
		
		
		@Test(priority=3)
		public void verifyNotificationClick()
		{
			TestAgentPage.notificationMessageClick();
			Assert.assertTrue(TestAgentPage.IsbotStarted());
			
		}
		
		
		@Test(priority=4)
		public void verifyGetStarted() throws InterruptedException
		{
			
			TestAgentPage.switchToAvaamoChatBot();
			TestAgentPage.getIraDefaultResponse();
			
				
		}
		@Test(priority=5)
		public void getMessagesFromBot()
		{
			TestAgentPage.ToggleClick();
			TestAgentPage.sendMessage();
			TestAgentPage.getIraResponse();
			
		}
		@Test(priority=6)
		public void getMenuOptions()
		{
			TestAgentPage.StartOverClick();
			TestAgentPage.clickLatestDownloadBtn();
			
		}
		@Test(priority=7)
		public void sendQueryTestBot() 
		{
			
			TestAgentPage.PolicyDetails(Constants.PolicyHolderFullName,Constants.PolicyHolderAddress);
			
			
		}
		@Test(priority=8)
		public void sendQueryNewTest() throws InterruptedException
		{
			
			TestAgentPage.NewTestInput();
			
			
		}
		
		
		@AfterTest
		public void teardown()
		{
			driver.quit();
		}
		
	
	
}
