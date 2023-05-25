package com.fourfinance.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class PageObject {

    public static final String OPEN_API_LIBRARY = "https://openlibrary.org/";

    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
    private static final String CHROME_DRIVER_PATH = "drivers/chrome/chromedriver";
//    private static final String CHROME_DRIVER_PATH = "drivers/chrome/chromedriver_linux";
//    private static final String CHROME_DRIVER_PATH = "drivers/chrome/chromedriver_win.exe";

    public static WebDriver driver;
    public static Wait wait;

    public PageObject() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            WebDriver chromeDriver = new ChromeDriver();
            chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            chromeDriver.manage().window().maximize();
            driver = chromeDriver;
            LOG.info("Chrome driver is set up!");
        }
        if (wait == null) {
            wait = new Wait(driver);
        }
    }

    public static void openPage() {
        driver.get(OPEN_API_LIBRARY);
        wait.forLoading(OPEN_API_LIBRARY);
        LOG.info("Loading open library page.");
    }

    public void quit(){
        driver.close();
    }
}
