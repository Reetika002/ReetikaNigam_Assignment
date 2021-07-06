package com.jpmorgan.pages;

import com.jpmorgan.utility.AbsolutePath;
import com.jpmorgan.utility.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/*BasePage class is the parent of all the Page classes. 
 This class is used to instantiate the driver and opens the application url in  the configured browser*/
public class BasePage {

	protected static WebDriver driver = null;
	public static Properties prop = null;
	private static File file = null;
	private static FileInputStream fis = null;
	protected static int hold;
	protected static Wait<WebDriver> wait;
	public static String scenarioName;
	public static AbsolutePath absPath = new AbsolutePath("JPMorgan_Testing");

	static {
		setProperty();
	}

	public static void setProperty() {
		file = new File(absPath.path() + "src\\main\\resources\\configs\\configuration.properties");
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// global wait
		hold = Integer.valueOf(BasePage.prop.getProperty("globalWait"));
	}

	// Default constructor
	public BasePage() {

	}

	// Parameterized constructor to initialize the the browsers
	@SuppressWarnings("deprecation")
	public BasePage(String browser, String appURL) {
		if ("Chrome".equalsIgnoreCase(browser)) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.setHeadless(true);
			chromeOptions.addArguments("--window-size=1920x1080");
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--acceptInsecureCerts=true");
			driver = new ChromeDriver(chromeOptions);
			Constants.WebdriverExists = true;

		} // End of If

		else if ("FF".equalsIgnoreCase(browser)) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if ("IE".equalsIgnoreCase(browser)) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions()
					.destructivelyEnsureCleanSession();
			internetExplorerOptions.setCapability("nativeEvents", true);
			internetExplorerOptions.setCapability("unexpectedAlertBehaviour", "accept");
			internetExplorerOptions.setCapability("ignoreProtectedModeSettings", true);
			internetExplorerOptions.setCapability("disable-popup-blocking", true);
			internetExplorerOptions.setCapability("enablePersistentHover", false);
			internetExplorerOptions.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(internetExplorerOptions);
			Constants.WebdriverExists = true;
		}
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// fluent wait
		wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(4, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(TimeoutException.class)
				.withMessage("WebSite didn't Opened Sucessfully or This Screen is not Applicable");

	}

	public static void resetApplicationCache(String appUrl) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(true);
		chromeOptions.addArguments("--window-size=1920x1080");
		driver = new ChromeDriver(chromeOptions);
		driver.navigate().to(appUrl + "/home/resetCache");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(appUrl);
		driver.quit();
		Constants.WebdriverExists = true;

	}
}
