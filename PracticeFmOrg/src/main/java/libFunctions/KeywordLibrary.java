package libFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

//import coreFramework.FrameworkBase;

public class KeywordLibrary extends FrameworkBase {
	public static String mytimestamp;
	ExtentTest test = null;
	
	public KeywordLibrary() {
		test = ExtentTestManager.getTest();
	}
	

	public void ExpWaitByXpath(WebDriver driver,int n, String elexpath) throws InterruptedException {

		try {
			
			WebDriverWait wait = new WebDriverWait(driver,n);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elexpath)));
			
			test.log(Status.INFO, "Waiting for an element is successful");
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	public void ClickElementbyXpath(WebDriver driver,String eleXpath) throws InterruptedException {

		try {
			
			WebElement element = driver.findElement(By.xpath(eleXpath));
			element.click();			
			Thread.sleep(2000);
			test.log(Status.PASS, "Element click is successful");

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	
	public String GetTextFromElementByXpath(WebDriver driver,String eleXpath, ExtentTest test) throws InterruptedException {
		String extractedtext = null;
		try {
			
			WebElement element = driver.findElement(By.xpath(eleXpath));
			extractedtext = element.getText();
			
			Thread.sleep(2000);
			test.log(Status.PASS, "Extracted text "+extractedtext+"  from element is successful");

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		return extractedtext;

	}
	
	public String GetTextFromElementByXpathAndVerifyText(WebDriver driver,String eleXpath, String ExpectedText, ExtentTest test) throws InterruptedException {
		String extractedtext = null;
		try {
			
			WebElement element = driver.findElement(By.xpath(eleXpath));
			extractedtext = element.getText();
			Thread.sleep(1000);
			if (ExpectedText.equals(extractedtext)) {
				Assert.assertEquals(extractedtext, ExpectedText);
				test.log(Status.PASS, ""+extractedtext+ " is matched with Expected text "+ExpectedText+"");
				}
			else {
				Assert.fail(""+extractedtext+ " is not matched with Expected text "+ExpectedText+"");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		return extractedtext;

	}
	public void DropDownValuesComparisonWithExpectedValues(WebDriver driver, String dropdownfieldxpath, String ExpectedDropDownValues) throws InterruptedException {
		
		try {
			
			
			WebElement dropdownfieldElement = driver.findElement(By.xpath(dropdownfieldxpath));
			Select ddfield = new Select(dropdownfieldElement);
			List<WebElement> dropdownoptions = ddfield.getOptions();
			ArrayList<String> arr = new ArrayList<String>();
			for (int i = 0; i < dropdownoptions.size(); i++) {
				String drop_down_values = dropdownoptions.get(i).getText();
				arr.add(drop_down_values);
				System.out.println("dropdown values are " + drop_down_values);
			}
			
			
		
			String[] items = ExpectedDropDownValues.split(";");
			List<String> itemList = Arrays.asList(items);
			
			for (int i = 0; i < itemList.size(); i++) {
				System.out.println("print first" + items[i]);
			}

			boolean resultofarraycompare = Arrays.equals(arr.toArray(), itemList.toArray());
			if (resultofarraycompare == true) {
				Assert.assertEquals(arr.toArray(), itemList.toArray(), "please report this issue drop down values are not correct");
				test.log(Status.PASS, "List of values displayed in mail box drop down is correct and right order");
				System.out.println("Drop down values are matched correctly");
			}
			else{
				Assert.fail("Drop down value is not matched");
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		

	}
	
	public String FileUploadChromeByXpath(WebDriver driver,String eleXpath, String completefilepath) throws InterruptedException {
		String extractedtext = null;
		try {
			
			driver.findElement(By.xpath(eleXpath)).sendKeys("C:\\Jey\\Automation\\TestAutomation\\Resources\\Testingupload.txt");
			 Thread.sleep(2000);
			
		//	WebElement uploadElement = driver.findElement(By.id("fileUploadFile"));
			//((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('style')", uploadElement);
			//uploadElement.sendKeys("C:\\Jey\\Automation\\EMTestAutomation\\Resources\\Testingupload.txt");

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		return extractedtext;

	}
	
	
	public void ScrollDownUsingActionandClickonElement(WebDriver driver,String eleXpath) throws InterruptedException {
		String extractedtext = null;
		try {
			
			 Actions action=new Actions(driver);
		       action.sendKeys(Keys.PAGE_DOWN).build().perform();
		        Thread.sleep(3000);
		        WebElement elementtobeclicked = (new WebDriverWait(driver, 20))
						.until(ExpectedConditions.elementToBeClickable(By.xpath("eleXpath")));
		        elementtobeclicked.click();
		        test.log(Status.PASS, "Scroll Down and click is successful");

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}


	}
	
	public void SendKeysByXpath(WebDriver driver,String elexpath, String valuetobeentered) throws InterruptedException {

		try {
			driver.findElement(By.xpath(elexpath)).sendKeys(valuetobeentered);
				Thread.sleep(1000);
			test.log(Status.PASS, "Value entered in text box is successful");
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	public void ClearTextBoxbyXpath(WebDriver driver,String elexpath) throws InterruptedException {

		try {
			driver.findElement(By.xpath(elexpath)).clear();
				
			test.log(Status.PASS, "clear the text box field is successful");
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	
	public void IsElementDisabled(WebDriver driver, String elexpath, ExtentTest test) throws InterruptedException {

		try {
			
			boolean disablecheck = driver.findElement(By.xpath(elexpath)).isEnabled();
			if (disablecheck==false) {
				Assert.assertEquals(false, false);
				test.log(Status.PASS, "Element is disabled which is expected one");
				
			}
			else {
				Assert.fail("Element is not disabled, please check");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	
	
	public void VerifyAlertMessagebyXpath(WebDriver driver, String elexpath, String Expectedmsg, ExtentTest test) throws InterruptedException {

		try {

			Thread.sleep(1000);
			String textfromdialog = driver.findElement(By.xpath(elexpath)).getText();
	       String ExpectedValue = Expectedmsg;
			Assert.assertEquals(textfromdialog, ExpectedValue);
	        test.log(Status.PASS, "Expected alert message "+Expectedmsg+" displayed correctly");
		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	
	public void MovemouseHoverAndClick(WebDriver driver, String elexpath) throws InterruptedException {

		try {

			
			Actions actions = new Actions(driver);
			WebElement Element = driver.findElement(By.xpath(elexpath));
			actions.moveToElement(Element);
			actions.click().build().perform();
			
			Thread.sleep(2000);
			
	        test.log(Status.PASS, "Click on Element is successful");
		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	public int StringToIntConversionAndReturnbyXpath(WebDriver driver, String elexpath) throws InterruptedException {
		
		int convertedfromString = 0;

		try {
			String fromapplication = driver.findElement(By.xpath(elexpath)).getText();
			convertedfromString = Integer.parseInt(fromapplication);		
			Thread.sleep(2000);
	        test.log(Status.PASS, "Click on Element is successful");
		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		return convertedfromString;
	}
	
	public void SelectSpecifiedDateInCalendar(WebDriver driver, String datetobeselected) throws InterruptedException {

		try {

			 WebElement dateWidget = driver.findElement(By.xpath("//div[@class='xdsoft_calendar']//tbody/tr"));
			 List<WebElement> calrows=driver.findElements(By.xpath("//div[@class='xdsoft_calendar']//tbody/tr"));
			 List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
			 String a = "datetobeselected";
			 int colcount = columns.size();
			 int calrowcount = calrows.size();
			 boolean valuefound = false;
			 for (int i=1; i<=calrowcount; i++)
			 {
				 for (int j=1; j<=colcount; j++) {
					 String datevalue = driver.findElement(By.xpath("//div[@class='xdsoft_calendar']//tbody/tr["+i+"]/td["+j+"]")).getText();
					 if (datevalue.equals(a)) {
						 driver.findElement(By.xpath("//div[@class='xdsoft_calendar']//tbody/tr["+i+"]/td["+j+"]")).click();
						 valuefound=true;
					 }
					 if(valuefound)
						 break;
				 }
				 if(valuefound)
					 break;
			 }
		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	
	public void ClickUsingJavascriptByXpath(WebDriver driver, String eleXpath) throws InterruptedException {

		try {

			WebElement element = driver.findElement(By.xpath(eleXpath));
			
		 	JavascriptExecutor executor = (JavascriptExecutor)driver;
		 	executor.executeScript("arguments[0].click();", element);
		 	Thread.sleep(3000);
		 	
		 	
		 
		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}

	}
	
	public void AllocationFilterSelectAllRadioButtonSelection(WebDriver driver) throws InterruptedException {

		try {

			driver.findElement(By.cssSelector("#rdAllocationAll")).click();
		
			test.log(Status.PASS, "Click on Select All radio button is successful in Allocation Filter");
			Thread.sleep(2000);
		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	
	public void SLATypeFilterSelectAllRadioButtonSelection(WebDriver driver) throws InterruptedException {

		try {

			driver.findElement(By.cssSelector("#rdSLA_Type_All")).click();
			test.log(Status.PASS, "Click on Select All radio button is successful in SLA Type Filter");

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}

	}
	
	
	
	
	
	public void HamburgermenuMyWidgetDashboardClick(WebDriver driver) throws InterruptedException {

		try {

			 //click on hamburger menu icon   //i[@id='navrotate']
		      driver.findElement(By.xpath("//i[@id='navrotate']")).click();
		      test.log(Status.PASS, "Click on Hamburger menu is successful");
		      //click on my widget  //a[@id='My_Widgets']
		      driver.findElement(By.xpath("//a[@id='My_Widgets']")).click();
		      test.log(Status.PASS, "Click on My Widgets is successful");
		      //click on Dashboaard  //a[@id='Dashboard']
		      driver.findElement(By.xpath("//a[@id='Dashboard']")).click();
		      test.log(Status.PASS, "Click on Dashboard is successful");
		      Thread.sleep(2000);

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}

	}
	
	public void ClickonFirstURNFromResultsGrid(WebDriver driver) throws InterruptedException {

		try {

			WebElement URNlink = driver.findElement(By.xpath("//table[@id='results']/tbody/tr[1]/td[2]"));
			//WebElement elementcreate = driver.findElement(By.id("btnUpdate"));
		 	JavascriptExecutor executor = (JavascriptExecutor)driver;
		 	executor.executeScript("arguments[0].click();", URNlink);
		 	Thread.sleep(3000);
		 	test.log(Status.PASS, "Click on Request's URN is successful");
		 	driver.findElement(By.xpath("//div[contains(text(),'Email Details')]")).isDisplayed();

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}

	}
	
	
	
	public void Javascriptclick(WebDriver driver, String xpathexp) throws InterruptedException {

		try {

			WebElement elename = driver.findElement(By.xpath(xpathexp));
			//WebElement elementcreate = driver.findElement(By.id("btnUpdate"));
		 	JavascriptExecutor executor = (JavascriptExecutor)driver;
		 	executor.executeScript("arguments[0].click();", elename);
		 	Thread.sleep(3000);
		 	test.log(Status.PASS, "Click on Element is successful");
		 	

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
					
			Assert.fail(s);

		}

	}
	
	public ArrayList<String> AddvaluesToArrayList(String elexpath) throws InterruptedException {
		ArrayList<String> arr = new ArrayList<String>();
		try {

			WebElement mailboxDropdown = driver.findElement(By.xpath(elexpath));
			Select mailboxdd = new Select(mailboxDropdown);
			List<WebElement> mbdropdown = mailboxdd.getOptions();
			
			for (int i = 0; i < mbdropdown.size(); i++) {
				String drop_down_values = mbdropdown.get(i).getText();
				arr.add(drop_down_values);
				System.out.println("dropdown values are " + drop_down_values);
			}
			// login
			
			test.log(Status.PASS, "Drop down values added successfully in Array List");
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		return arr;

	}
	
	
	
	
	
	public ArrayList<String> SplitExceldataAndStoreInArray(String OptionsInExcel) throws InterruptedException {
		ArrayList<String> itemList = new ArrayList<String>();
		try {
			String sampleString = OptionsInExcel;
			
				String[] items = sampleString.split(";");
				//itemList = Arrays.asList(items);
				itemList = new ArrayList<String>(Arrays.asList(items));
			// login
			
			test.log(Status.PASS, "Drop down values mentioned in excel added successfully in Array List");
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		return itemList;

	}
	
	public void DropDownSelectionByVisibleText(WebDriver driver,String DDXpath, String DropDownSelectionByVisibleText) throws InterruptedException {

		try {

			Select Mailboxfilterdbdd = new Select(driver.findElement(By.xpath(DDXpath)));
			Mailboxfilterdbdd.selectByVisibleText(DropDownSelectionByVisibleText);
			Thread.sleep(2000);
			test.log(Status.PASS, "Drop Down - "+DropDownSelectionByVisibleText+"  selection is successful");

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}

	}
	
	public String DropDownSelectionByIndex(WebDriver driver,String DDXpath, int indexvalue) throws InterruptedException {
		String Selectedvalue = null;
		try {

			Select Mailboxfilterdbdd = new Select(driver.findElement(By.xpath(DDXpath)));
			Mailboxfilterdbdd.selectByIndex(indexvalue);
			Thread.sleep(3000);
			test.log(Status.PASS, "Drop Down selection is successful");
			WebElement option = Mailboxfilterdbdd.getFirstSelectedOption();
			Selectedvalue = option.getText();
			test.log(Status.PASS, "Selected drop down value capture "+Selectedvalue+" is successful");
			

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		return Selectedvalue;

	}
	
	
	

	
	public String ClearSearchFieldAndEnterURNinResultsSearchBox(WebDriver driver,String URNTobeSearched) throws InterruptedException {
		String FirstURNname = null;
		try {
			
			driver.findElement(By.xpath("//div[@id='results_filter']//input[@type='search']")).clear();
			driver.findElement(By.xpath("//div[@id='results_filter']//input[@type='search']")).sendKeys(URNTobeSearched);
			Thread.sleep(2000);
			
				test.log(Status.PASS, "Search field value cleared and URN Entered in Search box is successful");
			

		
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		return FirstURNname;

	}
	
	public void ClearSearchField(WebDriver driver,String elexpath) throws InterruptedException {
		
		try {
			
			driver.findElement(By.xpath(elexpath)).clear();
			Thread.sleep(3000);
			test.log(Status.INFO, "Search field clear is successful");
					
		} catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		

	}
	
public void EnterFewCharactersAndSelectAutoPopulateValueInTextBoxByXpath(WebDriver driver,String elexpath1, String elexpath2, String FewCharsOfString, String AutopopuplatedValueTobeSelected) throws InterruptedException {
		
		try {
			
			driver.findElement(By.xpath(elexpath1)).sendKeys(FewCharsOfString);
			
			
			WebElement myDynamicElement = (new WebDriverWait(driver, 10))
					  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(elexpath2)));
			WebElement autoOptions = driver.findElement(By.xpath(elexpath2));

			List<WebElement> optionsToSelect = myDynamicElement.findElements(By.tagName("li"));
			for(WebElement option : optionsToSelect){
		        if(option.getText().equals(AutopopuplatedValueTobeSelected)) {
		        	System.out.println("Trying to select: "+AutopopuplatedValueTobeSelected);
		            option.click();
		            break;
					
		} 
			}
		}
			
			
		        catch (Exception e) {
			e.printStackTrace();
			String s = ExceptionUtils.getStackTrace(e);
			Assert.fail(s);
		}
		
		}

public boolean IsElementNotDisplayedByXpath(WebDriver driver, String eleXpath) throws InterruptedException {
	
	boolean valuedisplay=false;
		
	
	try {
		
		valuedisplay = driver.findElement(By.xpath(eleXpath)).isDisplayed();

		if (valuedisplay == true) {
			
			Assert.fail("Value is displayed, which is not expected one please check");
		}
		else {
			test.log(Status.PASS, "Element is not displayed which is expected one");
		}
		return valuedisplay;	
	} catch (Exception e) {
		valuedisplay = false;
		test.log(Status.PASS, "Element is not displayed which is expected one");
		return valuedisplay;
	}
	
}
public void IsElementDisplayedByXpath(WebDriver driver, String eleXpath) throws InterruptedException {
	
	boolean valuedisplay=false;
		
	
	try {
		
		valuedisplay = driver.findElement(By.xpath(eleXpath)).isDisplayed();

		if (valuedisplay == true) {
			test.log(Status.PASS, "Element is displayed which is expected one");
		}
		else {
			Assert.fail("Value is not displayed, which is not expected one please check");
		}

		
				
	} catch (Exception e) {
		valuedisplay = false;
		e.printStackTrace();
		String s = ExceptionUtils.getStackTrace(e);
		Assert.fail(s);
	}
}
public void ClickWithActionAndMoveToElement(WebDriver driver,String eleXpath) throws InterruptedException {
	
	try {

		WebElement element = driver.findElement(By.xpath(eleXpath));

		Actions actions = new Actions(driver);

		actions.moveToElement(element).click().perform();
		test.log(Status.PASS, "Actions based click is successful");

	
	} catch (Exception e) {
		e.printStackTrace();
		String s = ExceptionUtils.getStackTrace(e);
		Assert.fail(s);
	}

}

public String[] splitStringWithRequiredValueAndStoreInArraySizeTwo(WebDriver driver,String stringtobesplit, String stringToUseForSplit) throws InterruptedException {
	String[] storeinArray = new String[2];
	try {

		String tosearchforsplit = stringToUseForSplit;
		String str = stringtobesplit;
		storeinArray = str.split(tosearchforsplit);
		
		test.log(Status.PASS, "String split successful and stored in Array");
		
	
	} catch (Exception e) {
		e.printStackTrace();
		String s = ExceptionUtils.getStackTrace(e);
		Assert.fail(s);
	}
	return storeinArray;

}
public String SelectdropdownByContainsText(String xpath, String containstextshouldbe) throws InterruptedException {
	String selectedvalue = null;
	
	try {
		
		
		WebElement actualDropdown = driver.findElement(By.xpath(xpath));
		//String firstpart = "//";
		String secondpart = xpath;
		
		String thirdpart = "//option[(contains(text(),'";
		String fourthpart = containstextshouldbe;
		String fifthpart = "'))]";
		String finalxpath = secondpart+thirdpart+fourthpart+fifthpart;
		actualDropdown.findElement(By.xpath(finalxpath)).click();
		selectedvalue = actualDropdown.findElement(By.xpath(finalxpath)).getText();
		
		//actualDropdown.findElement(By.xpath("(//select[@id='ddlMailboxDashboard']//option[(contains(text(),'(0)'))])[2]")).click();
		Thread.sleep(2000);
		
		test.log(Status.PASS, "Dropdown value - "+containstextshouldbe+" selected successfully "); 
		

	
	} catch (Exception e) {
		e.printStackTrace();
		String s = ExceptionUtils.getStackTrace(e);
		Assert.fail(s);
	}
	return selectedvalue;

}
public String SelectdropdownByNotContainsText(String xpath, String notcontainstextshouldbe) throws InterruptedException {
    String selectedvalue = null;
    
    try {           
           
           WebElement actualDropdown = driver.findElement(By.xpath(xpath));
           String firstpart = "(";
           String secondpart = xpath;
           
           String thirdpart = "//option[not(contains(text(),'";
           String fourthpart = notcontainstextshouldbe;
           String fifthpart = "'))])[2]";
           String finalxpath = firstpart+secondpart+thirdpart+fourthpart+fifthpart;
           actualDropdown.findElement(By.xpath(finalxpath)).click();
           selectedvalue = actualDropdown.findElement(By.xpath(finalxpath)).getText();
           
           //actualDropdown.findElement(By.xpath("(//select[@id='ddlMailboxDashboard']//option[(contains(text(),'(0)'))])[2]")).click();
           Thread.sleep(2000);
           
                  test.log(Status.PASS, "Dropdown value - "+notcontainstextshouldbe+" selected successfully ");
           

    
    } catch (Exception e) {
           e.printStackTrace();
           String s = ExceptionUtils.getStackTrace(e);
           Assert.fail(s);
    }
    return selectedvalue;

}
public void CompareArraylists(ArrayList<String> Array1 ,ArrayList<String> Array2, ExtentTest test ) throws InterruptedException {

    try {
           for (String Value : Array1)
           {
                  int pass = 0;
                  for (int j = 0; j < Array2.size(); j++)
                  {      
                        if (Value.equals(Array2.get(j)))
                        {
                               pass =1;
                        }
                        
                  }
                  
                  if(pass!=1)
                  {
                        Assert.fail("Comparison of Arraylist - Elements are not matching");
                        test.log(Status.FAIL, "Comparison of Arraylist - Elements are not matching");
                  }
           }
    
    } catch (Exception e) {
           e.printStackTrace();
           String s = ExceptionUtils.getStackTrace(e);
           Assert.fail(s);
    }

}


}

