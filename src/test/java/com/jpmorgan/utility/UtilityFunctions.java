package com.jpmorgan.utility;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.jpmorgan.pages.BasePage;
import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class UtilityFunctions extends BasePage {

	static boolean flag = false;
	private static boolean isDisplayFlag = false;
	static int time = Integer.parseInt("3000");
	static Logger log = Logger.getLogger("devpinoyLogger");

	public static void waitForPageToLoad() throws InterruptedException {
		Thread.sleep(Integer.parseInt(prop.getProperty("globalWait")) * 100);
	}

	public static void waitForElementClickable(WebElement element) throws Exception {
//		String[] message = String.valueOf(element).split("->");
		try {
			log.debug("Waiting for the element to be clickable: " + String.valueOf(element));
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			log.debug("Exception in Class Utils | Method waitForElement: " + String.valueOf(element));
			log.debug("Issue in waiting for the element");
			throw e;
		}
	}

	public static  void waitSmall() throws Exception{
		Thread.sleep(10000);
	}

	public static void performAssertEquals(WebElement element, String expectedValue) {
		try {
			log.debug("Performing assert operation");
			Assert.assertEquals(element.getText(), expectedValue);
			log.debug("Expected value - '" + expectedValue + "match with Actual value - " + element.getText() + "\n");
		} catch (Exception e) {
			log.debug(expectedValue + " - text validation failed");
			log.debug("Exception in Class Utils | Method performAssertEquals" + String.valueOf(element));
		}
	}

	public static void waitForElementPresence(WebElement element) throws Exception {
//		String[] message = String.valueOf(element).split("->");
		try {
			// log.debug("Waiting for element visibility");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
			log.debug("Element Present on the page, Verification Passed: " + String.valueOf(element));

		} catch (Exception e) {
			log.debug("Exception in Class Utils | Method waitForElementPresence: " + String.valueOf(element));
			log.debug("Element Not Present on the page, Verification Failed");
			throw (e);
		}
	}

	public static void mouseHover(WebElement mainElement) throws Exception {
//		String[] message = String.valueOf(mainElement).split("->");
		try {
			log.debug("Hovering mouse on the element: " + String.valueOf(mainElement));
			Actions builder = new Actions(driver);
			builder.moveToElement(mainElement).build().perform();
			log.debug("Mouse hovered successfully: ");
		} catch (Exception e) {
			log.debug("Exception in Class Utils | Method mouseHover");
			log.debug("Issue in hovering mouse on the element: " + String.valueOf(mainElement));
			throw e;
		}

	}

	public static void scrollingToPageElementAdvanced(WebElement element) {
		Locatable element1 = (Locatable) element;
		Point p = element1.getCoordinates().onPage();
		System.out.println(p.getX());
		System.err.println(p.getY());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(" + p.getX() + "," + p.getY() + ");");

	}

	public static void scrollingToPageElementByCordinate(int x, int y) {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(" + x + "," + y + ")");

	}

	public static void scrollingByAction(WebElement element) {

		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();

	}

	public static void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);

	}

	public static void switchToMainFrame() {
		driver.switchTo().defaultContent();

	}

	public static void reloadPage() {
		driver.navigate().to(driver.getCurrentUrl());

	}

	public static boolean click(WebElement element) {
		try {
			if (element != null) {
				element.click();
				flag = true;
			} else {
				flag = false;
			}
			return flag;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static boolean submit(WebElement element) {
		try {
			if (element != null) {
				element.submit();
				flag = true;
			} else {
				flag = false;
			}
			return flag;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static void clear(WebElement element) {
		try {
			if (element != null) {
				element.clear();
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public static void sendKeys(WebElement element,String value) {
		try {
			if (element != null) {
				element.sendKeys(value);
				flag = true;
			} else {
				flag = false;
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public static boolean isDisplayed(WebElement element) {
		try {
			if (element != null) {
				isDisplayFlag = element.isDisplayed();
				if (isDisplayFlag == true)
					flag = true;
				else
					flag = false;
			} else {
				flag = false;
			}
			return flag;
		} catch (Exception ex) {
			return false;
		}

	}

	public static void getWindowHandle(WebElement element){
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to popup window

// Now you are in the popup window, perform necessary actions here

//		driver.switchTo().window(parentWindowHandler);  // switch back to parent window
	}

	public static void getScreenshot(Scenario scenario){
		Log.debug("Scenario ended: " + BasePage.scenarioName);
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				// This takes a screenshot from the driver at save it to the specified location
				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

				// Building up the destination path for the screenshot to save
				// Also make sure to create a folder 'screenshots' with in the cucumber-report
				// folder
				File destinationPath = new File(
						System.getProperty("user.dir") + "/generated/screenshots/" + screenshotName + ".png");

				// Copy taken screenshot from source location to destination location
				Files.copy(sourcePath, destinationPath);

				// This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			}
		}
	}
	// --------------------------------------------------------------------------------------------------------------------------------
	// Read data from the excel file. Takes two arguments - Test Case Name and
	// XLS_Reader object. Reads the test data from the Test Data excel and
	// return the test data in 2 D object array form
	public static Object[][] getData(String testCaseName, ExcelUtility xls, String sheetName) {
		// find test in the excel file
		// find number of columns in the test
		// find number of rows in the test
		// print the data of the test
		// put the data in object array

		// Get the start row index for the test data of the given Test Case name
		int testCaseStartIndex = 0;

		for (int rNum = 1; rNum <= xls.getRowCount(sheetName); rNum++) {
			if (testCaseName.equals(xls.getCellData(sheetName, 1, rNum))) {
				testCaseStartIndex = rNum;
				break;
			} // end of if
		} // end of for

		// Get the number of columns in the test data (which is available column
		// wise) for the given test case
		int testCaseDataColumnNamesStartIndex = testCaseStartIndex + 1;

		int colsOffset = 2;
		int Cols = colsOffset;
		while (!(xls.getCellData(sheetName, Cols, testCaseDataColumnNamesStartIndex).equals(""))) {
			Cols++;
		} // end of while

		int numberOfTestDataColumns = Cols - colsOffset;

		int testCaseDataStartIndex = testCaseStartIndex + 2;

		int rows = 0;
		while (!xls.getCellData(sheetName, colsOffset, (testCaseDataStartIndex + rows)).equals("")) {
			rows++;
		} // end of while
		int numberofTestDataSets = rows;

		// Store the test data sets of a single test case in an array of
		// HashTable. Each HasTable will contain one test data set
		Object[][] dataSetCollection = new Object[numberofTestDataSets][1];

		Hashtable<String, String> TestDataSet = null;
		String Datakey = "'";
		String Keyvalue = "";
		int index = 0;
		for (int r = testCaseDataStartIndex; r < (testCaseDataStartIndex + numberofTestDataSets); r++) {
			TestDataSet = new Hashtable<String, String>();

			for (int c = colsOffset; c < (numberOfTestDataColumns + colsOffset); c++) {

				Datakey = xls.getCellData(sheetName, c, testCaseDataColumnNamesStartIndex);
				Keyvalue = xls.getCellData(sheetName, c, r);
				TestDataSet.put(Datakey, Keyvalue);

			} // end of for
				// Once all the Column Name Value pair is stored in the
				// HashTable for a row, add this Hash Table in the 2 D array of
				// Objects
			dataSetCollection[index][0] = TestDataSet;
			index++;
		} // end of for

		return dataSetCollection;

	}// end of function

}// end of class
