package com.fourfinance.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Header {
    private static final Logger LOG = LogManager.getLogger(MainPage.class);

    @FindBy(xpath = "//div[@id='contentBody']//h2[contains(text(), 'Welcome to Open Library')]")
    private WebElement textWelcomeToOL;

    @FindBy(xpath = "//div[@class='search-component']//select[@aria-label='Search by']//option[@value='title']")
    private WebElement selectTitle;

    @FindBy(xpath = "//form[@class='search-bar-input']//input[@type='text']")
    private WebElement inputSearch;

    @FindBy(xpath = "//ul[@class='auth-component']//a[contains(text(),'Log In')]")
    private WebElement buttonLogIn;

    @FindBy(xpath = "//ul[@class='auth-component']//a[contains(text(),'Sign Up')]")
    private WebElement buttonSignUp;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public void openAndVerifyOALPage() {
        openPage();
        wait.waitUntilVisible(textWelcomeToOL);
        LOG.info("Open library page is loaded.");
    }

    public void selectAndVerifyLanguage(String language) {
        selectLanguage(language);
        LOG.info(language + " was clicked.");
        wait.waitUntilVisible(buttonLogIn);
        wait.waitUntilVisible(buttonSignUp);
        LOG.info("Language was changed to English.");
    }

    public void searchForBookByTitle(String book) {
        selectTitle.click();
        LOG.info("Title was selected");
        wait.waitUntilVisible(inputSearch);
        inputSearch.sendKeys(book);
        inputSearch.sendKeys(Keys.ENTER);
        LOG.info("Searching book by title.");
    }
}
