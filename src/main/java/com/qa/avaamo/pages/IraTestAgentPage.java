package com.qa.avaamo.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.avaamo.base.BasePage;
import com.qa.avaamo.util.Constants;
import com.qa.avaamo.util.ElementUtil;



public class IraTestAgentPage extends BasePage {

	

	WebDriver driver;
	ElementUtil util;
	
	/**
	 Page Objects
	 */
	By IRA_popup_ele=By.xpath("//div[@class='avm-bot-welcome-notification animated fadeInUp']");
	
	By Welcometxt=By.xpath("//h3//small[@class='avm-welcome-notification-message']");
	By GetStartedPopUp=By.xpath("//div[@class='avm-bot-get-started']");
	By GetStartedButton=By.xpath("//div//a[@class='get-started-link']");
	By IraChatBot=By.name("avaamoIframe");
	By IradefaultMessage=By.xpath("//div[contains(@class,'not-mine')]");
	By togglemenu=By.xpath("//div[contains(@title,'Toggle Menu')]");
	By Sendmessage=By.xpath("//div//textarea[@name='message']");
	By SendBtn=By.xpath("//div[contains(text(),'Send')]");
	By IraMessagelistresponse=By.cssSelector("#messages-list .not-mine .text-content");
	By CustomerMessagelistresponse=By.cssSelector("#messages-list .mine .text-content");
	By AllMessages=By.cssSelector("#messages-list .text-content");
	By SwitchToBot=By.xpath("//div[contains(@title,'Switch to bot menu')]");
	By StartOver=By.xpath("//a[contains(@actionname,'Start Over')]");
	By DownloadPolicylist=By.xpath("//div[@class='default_card_link']//a[text()='Download Motor Policy']");	
	By DownloadLink=By.xpath("//a[text()='Download']");
	By PolicyHolderFullName=By.xpath("//div//input[contains(@id,'single_line_text')]");
	By PolicyHolderAddress=By.xpath("//div//textarea[contains(@id,'data_capture')]");	
	By GenderOptions=By.xpath("//div//span[contains(@class,'composer__container__preview__option__circle')]");
	By StarRating= By.xpath("//span[@class='star-cb-group']//label[1]");
	By PicklistVisitedOften=By.xpath("//input[contains(@id,'picklist')]");
	By RareOption=By.xpath("//ul//li[contains(text(),'Rare')]");
	By SubmitBtn=By.cssSelector(".default_card_submit");
	By GoogleTestLink=By.xpath("//div//a[contains(text(),'Google')]");
	By GoogleCloseBtn=By.xpath("//div[contains(@id,'webview_container')]//button[@class='close']");
	By CallLink=By.xpath("//div//a[contains(text(),'Call')]");
	By AvaamoImg=By.cssSelector(".avaamo__icon .avm-bot-icon");
	WebElement TestAgentframe;
	/*
	 * 
	 * Constructor of Page Class
	 * 
	 */
	public IraTestAgentPage(WebDriver driver)
	{
		this.driver=driver;
		util=new ElementUtil(driver);
	}
	/*
	 * 3.PageMethods
	 * 
	 */
	public String getPageTitle()
	{
		//util.WaitforTitlePresent(Constants.IraPageTitle, Constants.WaitTimeInSec);
		String title=driver.getTitle();
		System.out.println("Page title is: "+title);
		return title;
	}
	
	public boolean iraNotificationPop()
	{
		util.WaitForElementPresent(Constants.WaitTimeInSec, IRA_popup_ele);
		return driver.findElement(IRA_popup_ele).isDisplayed();
		
	}
	
	public String getNotificationText()
	{
		util.WaitForElementPresent(Constants.WaitTimeInSec, Welcometxt);
		return driver.findElement(Welcometxt).getText();
	}
	
	public boolean notificationMessageEnabled()
	{
		util.WaitForElementPresent(Constants.WaitTimeInSec, Welcometxt);
		return driver.findElement(Welcometxt).isEnabled();		
	}
	
	public void notificationMessageClick()
	{
		if(notificationMessageEnabled())
		util.doClick(Welcometxt);		
	}
		
	public boolean isBotStarted()
	{
		util.WaitForElementPresent(Constants.WaitTimeInSec, GetStartedPopUp);
		Boolean IsBotStarted=driver.findElement(GetStartedPopUp).isDisplayed();
		if(IsBotStarted)
		{
			driver.findElement(GetStartedButton).click();
		
		}
		return IsBotStarted;
	}
		
	public void switchToAvaamoChatBot()
	{
		
		util.WaitForElementPresent(Constants.WaitTimeInSec, IraChatBot);
		TestAgentframe=util.GetElement(IraChatBot);
		driver.switchTo().frame(TestAgentframe);
		
		//String defaulttxt=driver.findElement(By.className("default_card_description")).getText();
	}
	
	public String getIraDefaultResponse()
	{
		util.WaitForElementPresent(Constants.WaitTimeInSec, IradefaultMessage);
		String defaulttxt=util.doGetText(IradefaultMessage);
		System.out.println(defaulttxt);
		return defaulttxt;
	}
	
	public void toggleClick()
	{
		util.doClick(togglemenu);
	}
	
	public boolean sendIsEnabled()
	{		
		return util.BtnIsEnabled(SendBtn);
	}
	
	public void sendMessage()
	{
		if(sendIsEnabled())
			util.SendMessage(Sendmessage, Constants.RenewPolicy);
			util.doClick(SendBtn);		
	}
	
	
	public List<WebElement> getIraResponse()
	{
		 util.WaitForElementPresent(Constants.WaitTimeInSec, IraMessagelistresponse);
		 List<WebElement> response=util.IraResponse(IraMessagelistresponse);
		 return response;
	}
	
	public List<WebElement> getClientResponse()
	{
		 util.WaitForElementPresent(Constants.WaitTimeInSec, IraMessagelistresponse);
		 List<WebElement> response=util.IraResponse(IraMessagelistresponse);
		 return response;
	}
	

	public void botMenuClick()
	{
		util.BtnIsEnabled(SwitchToBot);
		util.doClick(SwitchToBot);
	}
	
	public void StartOverClick()
	{
		botMenuClick();
		util.WaitForElementPresent(Constants.WaitTimeInSec, StartOver);
		util.BtnIsEnabled(StartOver);
		util.doClick(StartOver);
	}
	

	
	public void clickDownload()
	{
		//TestAgentframe=driver.findElement(By.name("avaamoIframe"));
		util.WaitForElementPresent(Constants.WaitTimeInSec, DownloadLink);
		util.doClick(DownloadLink);
		util.IraGetWindowHandles(TestAgentframe);
	}
	
	
	public void clickLatestDownloadBtn()
	{
		util.WaitForElementPresent(Constants.WaitTimeInSec, DownloadPolicylist);
		util.clickLatestDownloadBtn(DownloadPolicylist);		
		clickDownload();
	
	}
	
	
	public void PolicyDetails(String Fullname, String Addr) 
	{
		util.SendMessage(Sendmessage, Constants.TestBot);
		util.doClick(SendBtn);
		util.WaitForElementPresent(Constants.WaitTimeInSec, PolicyHolderFullName);
		util.doSendKeys(PolicyHolderFullName, Fullname);
		util.doSendKeys(PolicyHolderAddress, Addr);
		util.ClickFemaleGender(GenderOptions,Constants.GenderOptions);
		util.SelectPicklist(PicklistVisitedOften, RareOption);
		util.doClick(StarRating);
		util.doClick(SubmitBtn);
		
		
	}
	public void NewTestInput() throws InterruptedException
	{
		Thread.sleep(3000);
		if(sendIsEnabled()){
		util.SendMessage(Sendmessage, Constants.NewTest);
		util.doClick(SendBtn);
		util.WaitForElementPresent(Constants.WaitTimeInSec, GoogleTestLink);
		util.doClick(GoogleTestLink);
		Thread.sleep(3000);
		util.WaitForElementPresent(Constants.WaitTimeInSec, GoogleCloseBtn);
		util.doClick(GoogleCloseBtn);
		util.doClick(CallLink);
		Thread.sleep(3000);
		util.IraGetWindowHandles(TestAgentframe);
		
		}
	}
}
