package libFunctions;



import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import coreFramework.FrameworkBase;

public class ReusableJavascrpitfunctions extends FrameworkBase {
	public static String mytimestamp;
	ExtentTest test;
	
	public void JavascriptclickusingXpath(WebDriver driver, String xpath) throws InterruptedException {

		try {

			WebElement elename = driver.findElement(By.xpath(xpath));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
		 	executor.executeScript("arguments[0].click();", elename);
		 	Thread.sleep(3000);
		 	
		 			
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}
	}
	
	public void JavascriptScrolltogetElementinView(WebDriver driver, String xpath, ExtentTest test) throws InterruptedException {

		try {

			WebElement elename = driver.findElement(By.xpath(xpath));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", elename);
			Thread.sleep(500); 
		 	Thread.sleep(3000);
		 	test.log(Status.PASS, "Click on Element is successful");
		 			
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}
	}
	
	public void JavascriptWebpageScrolltoDesiredheight(WebDriver driver, String height) throws InterruptedException {

		try {
 
			String scrollheight = height;
			JavascriptExecutor executor = (JavascriptExecutor)driver;
		 	executor.executeScript("window.scrollTo(0,"+scrollheight+")");
		 	Thread.sleep(3000);
		 	test.log(Status.PASS, "Click on Element is successful");
		 			
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}
	}
	
	
	public void JavascriptWebpageScrolltoBottom(WebDriver driver) throws InterruptedException {

		try {

			JavascriptExecutor executor = (JavascriptExecutor)driver;
		 	executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 	Thread.sleep(3000);
		 	test.log(Status.PASS, "Click on Element is successful");
		 			
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}
	}
	public void JavascriptSendKeysByXpath(String xpath, String ValueToBePassed) throws InterruptedException {

		try {

			WebElement elementcreate = driver.findElement(By.xpath(xpath));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value="+ValueToBePassed+";", elementcreate);
		 	Thread.sleep(3000);
		 	test.log(Status.PASS, "Enter value "+ValueToBePassed+" is successful");
		 			
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}
	}
	
	
}