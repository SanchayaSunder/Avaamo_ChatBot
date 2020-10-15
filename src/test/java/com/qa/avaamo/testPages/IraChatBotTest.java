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
			TestAgentPage=new IraTestAgentPage(driver);
		}

		
		
		
		@Test(priority=1,enabled=true)
		public void verifyGetStarted()
		{
			 
			boolean isDisplayed=TestAgentPage.iraNotificationPop();
			Assert.assertTrue(isDisplayed);
			String WelcomeMsg=TestAgentPage.getNotificationText();
			if(WelcomeMsg!="")
			{
			Assert.assertEquals(WelcomeMsg, Constants.WelcomeMessage);
			System.out.println(WelcomeMsg);	
			}
			TestAgentPage.notificationMessageClick();
			Assert.assertTrue(TestAgentPage.isBotStarted());
			TestAgentPage.switchToAvaamoChatBot();
		}
				
	
		@Test(priority=2,enabled=true)
		public void verifyPageTitle()
		{
			 String title=TestAgentPage.getPageTitle();
			 Assert.assertEquals(title,Constants.IraPageTitle);
			 
		}
		@Test (priority=3,enabled=true, dependsOnMethods={"verifyGetStarted"})
		public void verifyGetDefaultResponse()
		{
			TestAgentPage.getIraDefaultResponse();
		}
		@Test(priority=4,enabled=true, dependsOnMethods = { "verifyGetDefaultResponse" })
		public void getMessagesFromBot()
		{
			TestAgentPage.toggleClick();
			TestAgentPage.sendMessage();
			TestAgentPage.getIraResponse();
			
		}
		@Test(priority=5,enabled=true, dependsOnMethods = { "verifyGetDefaultResponse" })
		public void getMenuOptions()
		{
			TestAgentPage.StartOverClick();
			TestAgentPage.clickLatestDownloadBtn();
			
		}
		@Test(priority=6,enabled=true, dependsOnMethods = { "verifyGetDefaultResponse" })
		public void sendQueryTestBot() 
		{
			
			TestAgentPage.PolicyDetails(Constants.PolicyHolderFullName,Constants.PolicyHolderAddress);
			
			
		}
		@Test(priority=7,enabled=true,dependsOnMethods = { "verifyGetDefaultResponse" })
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
