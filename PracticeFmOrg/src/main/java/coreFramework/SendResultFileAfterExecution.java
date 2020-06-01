package coreFramework;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SendResultFileAfterExecution {
	public static String mailurl= null; 
	public static String mailusername = null;
	public static String mailpassword = null;
	
	public static void sendResultFileAfterExecution(File Filename) {
		try {
			WebDriver driver;
			
			Properties prop=new Properties();
			FileInputStream fileData= new FileInputStream(Globals.ConfigFilePath);

			prop.load(fileData);
			//Parameters from Properties File
			mailurl = prop.getProperty("mailurl");
			mailusername = prop.getProperty("mailusername");
			mailpassword = prop.getProperty("mailpassword");
			
          // byte[] encodedpwd = Base64.encodeBase64(mailpassword.getBytes());
          //  System.out.println("Encoded pwd is " +new String(encodedpwd) );
            
            byte[] decodedpwd = Base64.decodeBase64(mailpassword);
            String uppwd = new String(decodedpwd, "UTF-8");
            //System.out.println("Encoded pwd is " +new String(decodedpwd) );

			
			System.setProperty("webdriver.chrome.driver", Globals.chromeDriverPath);
			driver = new ChromeDriver();
			
		    driver.get(mailurl);
		    driver.manage().window().maximize();
		    driver.findElement(By.id("username")).clear();
		    driver.findElement(By.id("username")).sendKeys(mailusername);
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys(uppwd);
	
		    driver.findElement(By.className("signinbutton")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//span[contains(text(),'New')]")).click();
		    //driver.findElement(By.xpath("//span[@title='Write a new message (N)']")).click();
		    Thread.sleep(5000);
		    WebElement To =  driver.findElement(By.xpath("//input[@aria-label='To']"));
		    To.sendKeys("tester1@gmail.com;tester2@gmail.com;tester3@gmail.com;");
		    Thread.sleep(3000);	
		
		    WebElement Cc =  driver.findElement(By.xpath("//input[@aria-label='Cc']"));
		    Cc.sendKeys("tester4@gmail.com;tester5@gmail.com;tester6@gmail.com;");
		    Thread.sleep(3000);	
		    
		    WebElement Subject= driver.findElement(By.xpath("//input[@aria-label='Subject,']"));
		    Subject.sendKeys("EON-Functional Test Automation Result");
		    
		    Thread.sleep(3000);
		    // driver.findElement(By.id("Signature")).clear();
		   
		    driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys("Hi Team,");
		    driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys(Keys.RETURN);
		    driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys("Please find the attached Test Automation Result.");
		    driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys(Keys.RETURN);
		    driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys(Keys.RETURN);
		    driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys("Thanks,");
		    driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys(Keys.RETURN);
		    driver.findElement(By.xpath("//div[@aria-label='Message body']")).sendKeys("Assurance - Automation Team");
		    
		    Thread.sleep(3000);
	/**	    
		    WebElement elementcreate = driver.findElement(By.xpath("(//button[@title='Attach'])[1]"));
		    JavascriptExecutor executor = (JavascriptExecutor) driver;
		    executor.executeScript("arguments[0].click();", elementcreate);
	**/	    
		    driver.findElement(By.xpath("(//button[@title='Attach'])[1]")).click();
		    Thread.sleep(2000);
		    
		    Robot robot = new Robot();
	        robot.setAutoDelay(2000);
	 
	        if(Filename.exists()) {
	        	StringSelection selection = new StringSelection(System.getProperty("user.dir")+"\\EON_Result_Report_Failed.zip");
		        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
	        	
	        }
	        else {
	        	StringSelection selection = new StringSelection(System.getProperty("user.dir")+"\\EON_Result_Report_Passed.zip");
		        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
	        	
	        }
	        
	        
	 
	        robot.setAutoDelay(1000);
	 
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	 
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
	 
	        robot.setAutoDelay(1000);
	 
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        Thread.sleep(10000);
		    
	        /**	    WebElement Attachment =driver.findElement(By.xpath("(//button[@title='Attach'])[2]"));
		    Attachment.sendKeys(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\Testingupload.txt");
		  
			
		    WebElement elementcreate = driver.findElement(By.xpath("//span[contains(text(),'Attach')]"));
		    JavascriptExecutor executor = (JavascriptExecutor) driver;
		    
		    executor.executeScript("arguments[0].value= System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\Testingupload.txt";", elementcreate);
		**/     
		    
		   
		    driver.findElement(By.xpath(" (//span[contains(text(),'Send')])[1]")).click();
		    Thread.sleep(5000);
		    
		    driver.findElement(By.xpath("//span[@class='_pe_h']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//span[contains(text(),'Sign out')]")).click();
		    Thread.sleep(2000);
		    driver.close();
		    
		} 
		    catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			
			
		}	
	}

}
