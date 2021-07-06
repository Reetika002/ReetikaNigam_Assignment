package com.jpmorgan.stepdefinitions;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.jpmorgan.pages.BasePage;
import com.jpmorgan.utility.Log;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;

public class Hooks extends BasePage {


	@Before(order = 1)
	public void beforeScenario(Scenario scenario) {
		BasePage.scenarioName = scenario.getName();
		Log.debug("Scenario to be executed: " + BasePage.scenarioName);
	}

	@After(order = 1)
	public void afterScenario(Scenario scenario) {

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
		driver.quit();
	}

}
