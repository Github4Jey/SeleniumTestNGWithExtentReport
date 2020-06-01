package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import coreFramework.FrameworkBase;
import utilities.ExtentTestManager;


public class CommonActions extends FrameworkBase {
	public static String mytimestamp;
	ExtentTest test = null;
	
	public CommonActions() {
		test = ExtentTestManager.getTest();
	}


	public void LoginActions(WebDriver driver, String username, String password, ExtentTest test) throws InterruptedException {

		try {

			driver.findElement(By.id("UserName")).clear();
			driver.findElement(By.id("UserName")).sendKeys(username);
			test.log(Status.PASS, "User Name entered successfully");
			// Enter password
			driver.findElement(By.id("Pwd")).clear();
			driver.findElement(By.id("Pwd")).sendKeys(password);
			test.log(Status.PASS, "Password entered successfully");
			// click n login button
			driver.findElement(By.id("btnLogin")).click();
			Thread.sleep(5000);

			// validate display of Landing page mail box filter option to ensure successful
			// login
			WebElement Regiondd = driver.findElement(By.xpath("//select[@id='lst_RegionName']"));
			Assert.assertTrue(Regiondd.isDisplayed());
			test.log(Status.PASS, "Login button clicked successfully and Landing page displayed");
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);


		}

	}

	public void DashboardFieldSelection(WebDriver driver, String view, String region,String sourcetype ,String month, ExtentTest test) throws InterruptedException {

		try {

			Select viewdd = new Select( driver.findElement(By.xpath("//select[@id='lst_view']")));
			viewdd.selectByVisibleText(view);
			Thread.sleep(2000);
			test.log(Status.PASS, "View dropdown selected as "+view);
			Select regiondd = new Select( driver.findElement(By.xpath("//select[@id='lst_RegionName']")));
			regiondd.selectByVisibleText(region);
			Thread.sleep(2000);
			test.log(Status.PASS, "Region dropdown selected as " +region);
			Select sourcedd = new Select( driver.findElement(By.xpath("//select[@id='lst_sourcetype']")));
			sourcedd.selectByVisibleText(sourcetype);
			Thread.sleep(2000);
			test.log(Status.PASS, "Source Type dropdown selected as "+sourcetype);
			driver.findElement(By.xpath("//input[@id='daterange']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[contains(text(),'"+month+"')]")).click();
			test.log(Status.PASS, month+" Month selected");
			driver.findElement(By.xpath("//a[@id='btnsubmit']")).click();
			test.log(Status.PASS, "Search button clicked Successfully");
			Thread.sleep(2000);
			test.log(Status.PASS, "Dashboard page data selection successful");
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);

		}

	}
	
	public void ProcessingPageHeaderComparison(WebDriver driver,String region,String sourcetype ,String selecteddate,String status, ExtentTest test) throws InterruptedException {

		try {
			Thread.sleep(5000);			
			String procheaderdate = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/th/span[2]")).getText();
			String procheadersource = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/th/span[4]")).getText();
			String procheaderregion = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/th/span[6]")).getText();
			String procheaderstatus = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/th/span[8]")).getText();
			if(procheaderstatus.equals(status)&& procheaderdate.contains(selecteddate) && procheadersource.equals(sourcetype) && procheaderregion.equals(region))
			{
				Assert.assertTrue(true, "All the header values displayed on selecting "+status+" count from dashboard is Correct");
				
			}						
			driver.findElement(By.xpath("//a[contains(text(),'Back')]")).click();
			test.log(Status.PASS, "All the header values displayed on selecting "+status+" count from dashboard is Correct");
			
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);

		}

	}
	
	public void TravesetoUploadTrueDuplicatePage(WebDriver driver, ExtentTest test) throws InterruptedException {

		try {
			driver.findElement(By.xpath("//div[@class='menuIcon']")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//span[contains(text(),'My Widgets')]")).click();
			test.log(Status.PASS, "My Widgets link is clicked");
			Thread.sleep(3000);				
			driver.findElement(By.xpath("//a/span[contains(text(),'Upload True Duplicates')]")).click();
			Thread.sleep(3000);
			test.log(Status.PASS, "Upload True Duplicates page displayed");
			
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);

		}

	}
	


}
