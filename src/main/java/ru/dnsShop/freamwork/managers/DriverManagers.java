package ru.dnsShop.freamwork.managers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static ru.dnsShop.freamwork.util.PropertiesConstants.*;

public class DriverManagers {

    private static WebDriver driver;

    private DriverManagers() {
    }

    private static TestPropManager props = TestPropManager.getTestPropManager();

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private static void initDriver() {
        System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROME_DRIVER));
        driver = new ChromeDriver();
        }


    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}
