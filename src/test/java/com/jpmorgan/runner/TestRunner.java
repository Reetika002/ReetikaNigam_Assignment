package com.jpmorgan.runner;
;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import com.jpmorgan.pages.BasePage;
import com.jpmorgan.utility.Constants;
import com.jpmorgan.utility.FileUtility;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@CucumberOptions(features = "src/test/resources/features/TC1_search_jpmorgan.feature",
        glue = {"com.jpmorgan.stepdefinitions"},
        dryRun = false,
        tags = {"@regression"},
        plugin = {"pretty", "com.cucumber.listener.ExtentCucumberFormatter:",
                "json:target/cucumber-reports/Cucumber.json"},
        monochrome = true

)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public static void setup() throws IOException {

        FileUtility.copyFolder(new File(Constants.ReportPath),
                new File(Constants.ReportPath + "/archive"));
        BasePage.setProperty();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
        String reportFolderName = sdf.format(new Date());
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath(
                Constants.ReportPath + "\\" + "cucumber_report_" + reportFolderName +".html");

    }

    @AfterClass
    private static void teardown() {
        Reporter.loadXMLConfig(new File("src/main/resources/configs/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", System.getProperty("os.name"));
        Reporter.setTestRunnerOutput("Sample test com.cliqgo.runner output message");
    }
}
