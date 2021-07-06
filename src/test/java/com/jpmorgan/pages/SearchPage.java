package com.jpmorgan.pages;

import com.jpmorgan.utility.UtilityFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//input[@name='q']")
    private WebElement googlesearchbox;

    @FindBy(xpath = "//input[@name='btnK']")
    private WebElement searchbtn;

    @FindBy(xpath = "//h3[text() = 'J.P. Morgan | Official Website']")
    private WebElement firstLink;

    @FindBy(xpath = "(//img[@class='first-logo'])[2]")
    private WebElement jpmorganLogo;


    public SearchPage() {
        PageFactory.initElements(driver, this);
    }

    public SearchPage(String browser, String appURL) {
        super(browser, appURL);
        PageFactory.initElements(driver, this);
    }

    public void searchText(String text) throws Exception {

        UtilityFunctions.click(googlesearchbox);
        UtilityFunctions.sendKeys(googlesearchbox, text);

    }

    public void clickOnSearchButton() throws Exception{
        UtilityFunctions.submit(googlesearchbox);
    }

    public void textPresent() throws Exception {

        UtilityFunctions.waitForElementPresence(firstLink);
        firstLink.isDisplayed();

    }

    public void clickFirstLink() throws Exception {

        UtilityFunctions.click(firstLink);
        UtilityFunctions.waitSmall();

    }


    public boolean jpMorganLogoPresent() throws Exception {

        UtilityFunctions.waitForElementPresence(jpmorganLogo);
        return jpmorganLogo.isDisplayed();

    }


}