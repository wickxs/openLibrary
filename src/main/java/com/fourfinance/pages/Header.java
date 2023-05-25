package com.fourfinance.pages;

import com.fourfinance.utils.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends PageObject {
    private static final Logger LOG = LogManager.getLogger(Header.class);

    @FindBy(xpath = "//div[@id='footer-locale-menu']//img")
    private WebElement buttonLanguage;

    public Header() {
        PageFactory.initElements(driver, this);
    }

    public void selectLanguage(String language) {
        buttonLanguage.click();
        By tabElementPath = By.xpath("//div[@id='footer-locale-menu']//a[@lang='" + language + "']");
        WebElement tabElement = driver.findElement(tabElementPath);
        wait.waitUntilVisible(tabElement);
        tabElement.click();
        LOG.info(tabElement + " language was clicked.");
    }
}
