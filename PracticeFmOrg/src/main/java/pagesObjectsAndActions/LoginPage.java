package pagesObjectsAndActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import coreFramework.FrameworkBase;

public class LoginPage extends FrameworkBase {

	@FindBy(how = How.XPATH, using = "//input[@id='user_email1']")
	   private WebElement LoginUserEmailTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='user_password']")
	   private WebElement PwdUserEmailTextBox;
	
	   //Constructor
	   public LoginPage(WebDriver driver){
	       //this.driver=driver;
	      // driver.get("https://www.toptal.com/");
	       //Initialise Elements
		   super();
	       PageFactory.initElements(driver, this);
	   }
	   
	   public void LoginTBDisplay(ExtentTest test){

		   LoginUserEmailTextBox.isDisplayed();
		   test.log(Status.PASS, "Login user name text box is displayed");

	    }
	   
	   public void PwdTBDisplay(ExtentTest test){

		   PwdUserEmailTextBox.isDisplayed();
		   test.log(Status.PASS, "Password text box is displayed");

	    }

	
	
}
