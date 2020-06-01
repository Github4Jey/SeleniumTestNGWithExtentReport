package tests;



import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import coreFramework.FrameworkBase;
import coreFramework.Globals;
import pagesObjectsAndActions.HomePage;
import pagesObjectsAndActions.LoginPage;
import utilities.ExcelUtility;
import utilities.ExtentTestManager;
import utilities.UtilityClass;


public class FirstSampleTSwithPageObjAndActions extends FrameworkBase{
	
	
	Map<String,String> map;
	
	ITestResult result;
	HomePage homepage;
	LoginPage loginpage;
	
	
	public FirstSampleTSwithPageObjAndActions(){
	super();
	}

	
	
	@Test
	public void LoginTextBoxDisplayValidationwithHomePageFirst(){		
		try {
			
			String name = new Object(){}.getClass().getEnclosingMethod().getName();	
			test.log(Status.INFO, "before excel read"+description);
			
			//String name = new Object(){}.getClass().getEnclosingMethod().getName();	
			map= testData.readExcel("FirstTCdescription");
		//	String username = map.get("UserName");
			//String password = map.get("Password");
			homepage = new HomePage(driver);
			loginpage = new LoginPage(driver);
			test.log(Status.INFO, description);
			homepage.LinkdisplayCheck(test);	
			test.log(Status.INFO, description);
			homepage.HTTButtonDisplayCheck(test);
			test.log(Status.INFO, description);
			homepage.LoginLinkclick(test);
			test.log(Status.INFO, description);
			Thread.sleep(5000);
			loginpage.LoginTBDisplay(test);
			test.log(Status.INFO, description);
			//CommonActions obj = new CommonActions();
			test.log(Status.INFO,MarkupHelper.createLabel(name + " Test Case Execution COMPLETED", ExtentColor.PINK));
						
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);
		}
		
	}
	
	
	
	
		

	
}

