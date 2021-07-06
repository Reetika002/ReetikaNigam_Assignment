package com.jpmorgan.stepdefinitions;

import com.jpmorgan.pages.BasePage;
import com.jpmorgan.pages.SearchPage;
import com.jpmorgan.utility.Constants;
import com.jpmorgan.utility.Log;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.Assert;

public class SearchTest extends BasePage {

    private final String filepath = Constants.SikuliImagesPath;

    SearchPage searchPage;

    @Given("^open browser and go to google\\.com$")
    public void openBrowserAndGoToGoogleCom() {
        searchPage = new SearchPage(prop.getProperty("browser"), prop.getProperty("testsiteURL"));
        Log.info("Google has launched");
    }

    @When("^enter \"([^\"]*)\" on search box$")
    public void enterOnSearchBox(String text) throws Exception {
        searchPage.searchText(text);
    }

    @And("^click on search button$")
    public void clickOnSearchButton() throws Exception {
        searchPage.clickOnSearchButton();
    }

    @And("^user should be able to see the results$")
    public void userShouldBeAbleToSeeTheResults() throws Exception {
        searchPage.textPresent();
    }

    @And("^click on the first result$")
    public void clickOnTheFirstResult() throws Exception {
        searchPage.clickFirstLink();
    }

    @Then("^verify that J\\.P\\. Morgan logo is shown$")
    public void verifyThatJPMorganLogoIsShown() throws Exception {
        boolean logoPresent = searchPage.jpMorganLogoPresent();
        Assert.assertTrue(logoPresent);

        // this will work only with JDK 8 or below
        // using sikuli tool - capture logo image and store it on src/main/resources/siluli_images/logo.png

        //find the image in the screen
        Screen screen = new Screen();
        Pattern pattern = new Pattern(filepath +"\\"+ "logo.png");
        Match c = screen.find(pattern);

        //create a region with that image, means consider only that specific region where the reference image is present
        //c.getRect() gives the region coordinates of the image
        Region r=new Region(c.getRect());
        System.out.println(r.text());
        Assert.assertEquals(r.text(),"J.P Morgan");
//        Match img1 = screen.exists("img1.png");
//        assertTrue(img1 != null);
    }


}
