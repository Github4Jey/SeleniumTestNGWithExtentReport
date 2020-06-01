package pagesObjectsAndActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import coreFramework.FrameworkBase;

public class HomePage extends FrameworkBase {
	
	@FindBy(how = How.LINK_TEXT, using = "Apply as a Freelancer")
	   private WebElement developerApplyButton;
	
	@FindBy(how = How.LINK_TEXT, using = "Hire Top Talent")
	   private WebElement HireTopTalentButton;
	
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log In')]")
	   private WebElement LoginLnk;

	   //Constructor
	   public HomePage(WebDriver driver){
	       //this.driver=driver;
	      // driver.get("https://www.toptal.com/");
	       //Initialise Elements
		   super();
	       PageFactory.initElements(driver, this);
	   }
	   
	   public void LinkdisplayCheck(ExtentTest test){

		   developerApplyButton.isDisplayed();
		   test.log(Status.PASS, "Freelance Apply button is displayed");

	    }
	   
	   public void HTTButtonDisplayCheck(ExtentTest test){

		   HireTopTalentButton.isDisplayed();
		   test.log(Status.PASS, "Hire Top Talent button is displayed");

	    }
	   
	   public void LoginLinkclick(ExtentTest test){

		   LoginLnk.click();
		   test.log(Status.PASS, "Login Link is displayed");

	    }
	   

}
