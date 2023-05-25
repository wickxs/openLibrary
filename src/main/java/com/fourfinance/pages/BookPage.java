package com.fourfinance.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookPage extends Header{

    private static final Logger LOG = LogManager.getLogger(BookPage.class);

    @FindBy(xpath = "//div[@class='work-title-and-author desktop']//h1[@class='work-title']")
    WebElement workTitle;

    @FindBy(xpath = "//div[@class='work-title-and-author desktop']//a[@itemprop='author']")
    WebElement author;

    @FindBy(xpath = "//dt[contains(text(),'ISBN 13')]//following-sibling::dd[@itemprop='isbn']")
    WebElement isbn;

    public void checkWorkTitle(String workTitleExpected){
        LOG.info("Checking if correct book page was loaded.");
        wait.waitUntilVisible(workTitle);
        Assertions.assertThat(workTitle.getText()).isEqualTo(workTitleExpected);
        LOG.info(workTitle.getText() + " is equal to " + workTitleExpected);
    }

    public String getIsbn(){
        return isbn.getText();
    }

    public String getAuthorName(){
        return author.getText();
    }
}
