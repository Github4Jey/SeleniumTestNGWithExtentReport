package coreFramework;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumBase {

	public static final String BY_TYPE_ID = "id";
	public static final String BY_TYPE_XPATH = "xpath";
	public static final String BY_TYPE_XPATH_JS = "xpath";
	public static final String BY_TYPE_CSSSELECTOR = "cssSelector";
	public static final String BY_TYPE_NAME = "name";
	public static final String BY_TYPE_LINKTEXT = "linkText";
	public static final String BY_TYPE_CLASSNAME = "className";
	public static final String BY_TYPE_TAGNAME = "tagName";
	public static final String DROPDOWN_TYPE_INDEX="selectByIndex";
	public static final String DROPDOWN_TYPE_VALUE="selectByValue";
	public static WebElement element;
	public static WebDriver driver;

	//**  *Check if Web Element exists  */
	public static Boolean doesElementExists(String data,String byType) {

		Boolean exists = true;
		try {
			if (byType.equals(SeleniumBase.BY_TYPE_ID)) {	    	    
				driver.findElement(By.id(data));
				//System.out.println("ID from boolean"+ driver.findElement(By.id(data)));
			} else if (byType.equals(SeleniumBase.BY_TYPE_XPATH)) {	    	
				driver.findElement(By.xpath(data));	    	
			} else if (byType.equals(SeleniumBase.BY_TYPE_CSSSELECTOR)) {
				driver.findElement(By.cssSelector(data));
			}  
			else if (byType.equals(SeleniumBase.BY_TYPE_NAME)) {
				driver.findElement(By.name(data));
			} else if (byType.equals(SeleniumBase.BY_TYPE_LINKTEXT)) {
				driver.findElement(By.linkText(data));
			} else if (byType.equals(SeleniumBase.BY_TYPE_CLASSNAME)) {
				driver.findElement(By.className(data));
			} else if (byType.equals(SeleniumBase.BY_TYPE_TAGNAME)) {
				driver.findElement(By.tagName(data));
			} else {
				Assert.fail("The By type for WebElement does not exists!");
			}

		} catch (Exception e) {
			exists = false;	    
		}	
		System.out.println("ID from boolean"+data+ exists);
		return exists;
	}

	//**  *Get the details of Web Element  */
	public static WebElement getElement(String data,String byType) {

		try {
			if (byType.equals(SeleniumBase.BY_TYPE_ID)) {	    	    
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(data)));
				element = driver.findElement(By.id(data));
			} else if (byType.equals(SeleniumBase.BY_TYPE_XPATH)) {	    	
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(data)));		
				element=driver.findElement(By.xpath(data));	    	

			} else if (byType.equals(SeleniumBase.BY_TYPE_CSSSELECTOR)) {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(data)));			
				element = driver.findElement(By.cssSelector(data));
			}  
			else if (byType.equals(SeleniumBase.BY_TYPE_NAME)) {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(data)));			
				element = driver.findElement(By.name(data));
			} else if (byType.equals(SeleniumBase.BY_TYPE_LINKTEXT)) {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(data)));			
				element = driver.findElement(By.linkText(data));
			} else if (byType.equals(SeleniumBase.BY_TYPE_CLASSNAME)) {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(data)));			
				element = driver.findElement(By.linkText(data));
			} else if (byType.equals(SeleniumBase.BY_TYPE_TAGNAME)) {
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(data)));			
				element = driver.findElement(By.tagName(data));
			} else {
				Assert.fail("The By type for WebElement does not exists!");
			}
		} catch (Exception e) {
			Assert.fail("The WebElement " + data + " is not found!");
		}
		return element;

	}

	//**  *Select Drop down  */
	public static void selectDropDown(WebElement element, String data, String type) {

		try {
			if (type.equals(SeleniumBase.DROPDOWN_TYPE_INDEX)) {		    	
				Select sel = new Select(element);
				sel.selectByIndex(Integer.parseInt(data));
			} else if (type.equals(SeleniumBase.DROPDOWN_TYPE_VALUE)) {
				Select sel = new Select(element);
				sel.selectByValue(data);
			} else {
				Assert.fail("The type for WebElement does not exists!");
			}

		} catch (Exception e) {
			Assert.fail("The WebElement " + data + " is not found!");
		}



	}

	//**  Check box - Checking */
	public static void checkbox_Checking(WebElement checkbox) {

		try{	
			boolean checkstatus;
			checkstatus = checkbox.isSelected();
			if (checkstatus == true) {
				//System.out.println("Checkbox is already checked");
			} else {
				checkbox.click();
				//System.out.println("Checked the checkbox");
			}
		} catch (Exception e) {
			Assert.fail("The WebElement " + checkbox + " checking is not successful!");
		}

	}

	//**  Check box - Unchecking */
	public static void checkbox_Unchecking(WebElement checkbox) {

		try{
			boolean checkstatus;
			checkstatus = checkbox.isSelected();
			if (checkstatus == true) {
				checkbox.click();
				//System.out.println("Checkbox is unchecked");
			} else {
				//System.out.println("Checkbox is already unchecked");
			}
		} catch (Exception e) {
			Assert.fail("The WebElement " + checkbox + " unchecking is not successful!");
		}

	}

	//** Select Radio button  */		
	public static void radiobutton_Select(WebElement Radio) {
		try{
			boolean checkstatus;
			checkstatus = Radio.isSelected();
			if (checkstatus == true) {
				System.out.println("RadioButton is already checked");
			} else {
				Radio.click();
				System.out.println("Selected the Radiobutton");
			}
		} catch (Exception e) {
			Assert.fail("The WebElement " + Radio + " selection is not successful!");
		}
	}

	//** De-Select Radio button  */
	public static void radioButton_Deselect(WebElement Radio) {
		try{
			boolean checkstatus;
			checkstatus = Radio.isSelected();
			if (checkstatus == true) {
				Radio.click();
				System.out.println("Radio Button is deselected");
			} else {
				System.out.println("Radio Button was already Deselected");
			}
		}catch (Exception e) {
			Assert.fail("The WebElement " + Radio + " De-select is not successful!");
		}
	}

	//** Drag and Drop Operation  */
	public static void dragAndDrop(WebElement fromWebElement,
			WebElement toWebElement) {
		Actions builder = new Actions(driver);
		builder.dragAndDrop(fromWebElement, toWebElement);
	}

	public static void dragAndDrop_Method2(WebElement fromWebElement,
			WebElement toWebElement) {
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(fromWebElement)
				.moveToElement(toWebElement).release(toWebElement).build();
		dragAndDrop.perform();
	}

	public static void dragAndDrop_Method3(WebElement fromWebElement,
			WebElement toWebElement) throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.clickAndHold(fromWebElement).moveToElement(toWebElement)
		.perform();
		Thread.sleep(2000);
		builder.release(toWebElement).build().perform();
	}

	//**  Key Specific Methods */
	public static void MouseOver(WebElement element){
		try{
			Actions actObj=new Actions(driver);
			actObj.moveToElement(element).build().perform();
		} catch (Exception e) {
			Assert.fail("The MouseOver for the " + element + " is not working!");
		}
	}

	public static void pressKeyDown(WebElement element) {
		element.sendKeys(Keys.DOWN);
	}

	public static void pressKeyEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}

	public static void pressKeyUp(WebElement element) {
		element.sendKeys(Keys.UP);
	}

	public static void moveToTab(WebElement element) {
		element.sendKeys(Keys.chord(Keys.ALT, Keys.TAB));
	}

	//** Clear text Field  */
	public static void clearTextField(WebElement element) {
		element.clear();
	}

	
	//** Perform Double CLick */
	public static void doubleClickWebelement(WebElement doubleclickonWebElement)
			throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.doubleClick(doubleclickonWebElement).perform();
		Thread.sleep(2000);

	}

	public static String getToolTip(WebElement toolTipofWebElement)
			throws InterruptedException {
		String tooltip = toolTipofWebElement.getAttribute("title");
		System.out.println("Tool text : " + tooltip);
		return tooltip;
	}

	/**	 *Scrolls down the page till the element is visible	 * @throws InterruptedException	 */
	public void scrollElementIntoView(WebElement element) throws InterruptedException {
		wait(1000);	
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		wait(1000);

	}

	/**	 *Scrolls down the page till the element is visible and clicks on the	 *element	 */
	public void scrollElementIntoViewClick(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}



	/**	 *Double clicks on the particular element	 */
	public static void doubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick((WebElement) element).perform();

	}


	/**	 *Refresh the current web page	 */
	public static void refreshPage() {
		driver.navigate().refresh();
	}


	/**	 *Navigate to next page	 */
	public static void moveToNextPage() {
		driver.navigate().forward();
	}

	/**	 *Navigate to previous page	 */
	public static void moveToPreviousPage() {	
		driver.navigate().back();
	}

	/**	 *Maximize the window	 */
	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}


	/**	 *Quit the application	 */
	public static void quit() {
		driver.quit();
	}

	/**	 *Closes the driver	 */
	public static void close() {
		driver.close();
	}


}

