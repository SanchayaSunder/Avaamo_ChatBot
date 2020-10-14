package com.qa.avaamo.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.avaamo.base.BasePage;

public class ElementUtil {
	WebDriver driver;
	BasePage basepage=new BasePage();
	
	public ElementUtil(WebDriver driver)
	{
		this.driver= driver;
	}
	/**
	 * GetElement from locator
	 */
	public WebElement GetElement(By locator)
	{
		WebElement element=null;
		
		try {
			element=driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Some exception while creating webelement");
			System.out.println(e.getMessage());
			
		}
		return element;
	}
	public void WaitForElementPresent(int timeout, By locator)
	{
		WebDriverWait wait =new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public String WaitforTitlePresent(String title, int timeout)
	{
		WebDriverWait wait =new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return title;
	}
	/*
	 * This method is used to click on element
	 * 
	 */
	public void doClick(By locator)
	{
		
		try {
			GetElement(locator).click();
		} catch (Exception e) {
			
			System.out.println("Some exception while clicking  web element");
			System.out.println(e.getMessage());
		}
		
	}
	public void doActionClick(By locator)
	{
		
		try {
			Actions action=new Actions(driver);
			action.click(GetElement(locator)).build().perform();
			
		} catch (Exception e) {
			
			System.out.println("Some exception while clicking  web element");
			System.out.println(e.getMessage());
		}
		
	}
	public void doSendKeys(By locator,String value)
	{
		try {
			GetElement(locator).sendKeys(value);
		} catch (Exception e) {
			
			System.out.println("Some exception while sending data to web element");
			System.out.println(e.getMessage());
		}
	}
	public void doActionSendKeys(By locator,String value)
	{
		try {
			Actions action =new Actions(driver);
			action.sendKeys(GetElement(locator), value).build().perform();
			GetElement(locator).sendKeys(value);
		} catch (Exception e) {
			
			System.out.println("Some exception while sending data to web element");
			System.out.println(e.getMessage());
		}
	}
	public String doGetText(By locator)
	{
		try {
			return GetElement(locator).getText();
		} catch (Exception e) {
			
			System.out.println("Some exception while getting data from web element");
			System.out.println(e.getMessage());
			return null;
		}
	}
	public boolean isElementDisplayed(By locator)
	{
		try {
			GetElement(locator).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}
	public static void takePageScreenshot(WebDriver driver, String fileName){
		File SrcFile = 	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(SrcFile, new File("./target/screenshots/"+fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void takeElementScreenshot(WebElement element, String fileName){
		File SrcFile = 	((TakesScreenshot)element).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(SrcFile, new File("./target/screenshots/"+fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean BtnIsEnabled(By Locator)
	{
		try{
			driver.findElement(Locator).isEnabled();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	public void SendMessage(By locator, String value)
	{
	
		BtnIsEnabled(locator);
		doSendKeys(locator,value);
		//doClick(locator);
	}
	
	public List<WebElement> IraResponse(By Locator)
	{
		try{
	
		List<WebElement> IraMessagelistresponse=driver.findElements(Locator);
		
		//System.out.println(IraMessagelistresponse.size());
		
		
		for(int i=0;i<IraMessagelistresponse.size();i++)
		{
		
			System.out.println("Ira Messages: "+IraMessagelistresponse.get(i).getText());
		
		}
		
		return IraMessagelistresponse;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public void clickLatestDownloadBtn(By Locator)
	{
		try
		{
			List<WebElement> totalstartover=driver.findElements(Locator);
		
			for(int k=(totalstartover.size()-1);k<totalstartover.size();k++)
			{
				totalstartover.get(k).click();
			}
		}
		catch(Exception e)
		{
			System.out.println("Some exception while clicking download button");
			System.out.println(e.getMessage());
		}
	}
	public void ClickFemaleGender(By Locator, String Option)
	{
		try
		{
		List<WebElement> GenderOptions=driver.findElements(Locator);
		
		WebElement Male=GenderOptions.get(0);
		WebElement Female=GenderOptions.get(1);
		Female.click();
		}
		catch(Exception e)
		{
			System.out.println("Some exception while selecting gender");
			System.out.println(e.getMessage());
		}
	}
	
	public void SelectPicklist(By Locator,By Locator2)
	{
		try{
			
			driver.findElement(Locator).click();
			WaitForElementPresent(Constants.WaitTimeInSec, Locator2);
			WebElement ulitem=driver.findElement(Locator2);
			ulitem.click();
			}
		catch(Exception e)
		{
			System.out.println("Some exception while selecting picklist data");
			System.out.println(e.getMessage());
		}
		
	}
	public void IraGetWindowHandles(WebElement ele)
	{
		try{
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		
	    driver.switchTo().window(tabs.get(1));
	    driver.close();
	    //driver.close();
	    driver.switchTo().window(tabs.get(0));
	    driver.switchTo().frame(ele);
		}
		catch(Exception e)
		{
			System.out.println("Some exception while getting the window handles");
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
