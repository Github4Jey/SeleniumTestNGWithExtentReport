package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import coreFramework.FrameworkBase;

public class UtilityClass extends FrameworkBase
{
 
	public static String getScreenshot(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		String path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
		
		File destination=new File(path);
		
		try 
		{
			FileUtils.copyFile(src, destination);
		} catch (IOException e) 
		{
			System.out.println("Capture Failed "+e.getMessage());
		}
		
		return path;
	}
	
	public static String getScreenshotAfterRequiredStep(WebDriver driver, boolean passStepFlagValue, String methodName, String StepIdentifyText)
	{
		String path = null;
		if(passStepFlagValue) {
			TakesScreenshot ts=(TakesScreenshot) driver;
			
			File src=ts.getScreenshotAs(OutputType.FILE);
			
			path=System.getProperty("user.dir")+"\\Screenshot\\RequiredPassedScreenshots\\"+ methodName + "_" + StepIdentifyText +".png";
			
			File destination=new File(path);
			
			try 
			{
				FileUtils.copyFile(src, destination);
			} catch (IOException e) 
			{
				System.out.println("Capture Failed "+e.getMessage());
			}
			
		}
		return path;
		
	}
	public static  String capturescreenshot(WebDriver driver, String screenshotName){
		try {
			overallExecutionStatus="Failed";
			TakesScreenshot ts = (TakesScreenshot)driver;
			String path=System.getProperty(	"user.dir");
			File source = ts.getScreenshotAs(OutputType.FILE);
			//String dest= "C:\\Users\\Jey\\"+screenshotName+".png"/
			String dest= "../Screenshot/"+screenshotName+".png";
			System.out.println(dest);
			File destination = new File(path+"\\Screenshot\\"+screenshotName+".png");
			FileUtils.copyFile(source, destination);
			
			System.out.println("Screenshot taken");
			return dest;

}
	catch (Exception e){
		System.out.println("Exception while taking screenshot "+e.getMessage());
		return e.getMessage();
	}

	}	
	
	

}