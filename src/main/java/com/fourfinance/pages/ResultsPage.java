package com.fourfinance.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage extends Header{
    private static final Logger LOG = LogManager.getLogger(ResultsPage.class);

    @FindBy(xpath = "//div[@id='contentHead']//h1[contains(text(),'Search Books')]")
    private WebElement textSearchBooks;

    @FindBy(xpath = "//div[@id='searchResults']//ul[@class='list-books']//li")
    private List<WebElement> listOfBooks;

    public ResultsPage() {
        PageFactory.initElements(driver, this);
    }

    public void selectBookByYear(String year, String bookTitle) {
        wait.waitUntilVisible(textSearchBooks);
        for (WebElement book : listOfBooks) {
            if (!book.findElements(By.xpath("//span[@class='resultPublisher']//span[contains(text(),'" + year + "')]")).isEmpty()){
                book.findElement(By.xpath("//a[contains(text(),'" + bookTitle + "')]")).click();
                LOG.info("Book with " + bookTitle + " and published year " + year + " was found.");
                return;
            }
        }
        LOG.info("Book with " + bookTitle + " and published year " + year + " was not found.");
        throw new NotFoundException("Book with title " + bookTitle + " and year published " + year + " was not found!");
    }
}
