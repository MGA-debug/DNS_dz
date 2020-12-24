package ru.dnsShop.freamwork.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.dnsShop.freamwork.Products.Product;
import ru.dnsShop.freamwork.managers.ManagerPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static ru.dnsShop.freamwork.managers.DriverManagers.getDriver;

public class BasePage {
    static List<Product> listProduct = new ArrayList<>();
    static Stack<Product> removedProduct = new Stack<>();


    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    public WebElement inputSearchProduct;

    protected ManagerPages app = ManagerPages.getManagerPages();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }


    public void scrollWithOffset(WebElement webElement, int x, int y) {
        String code = "window.scroll(" + (webElement.getLocation().x + x) + ","
                + (webElement.getLocation().y + y) + ");";

        ((JavascriptExecutor)getDriver()).executeScript(code, webElement, x, y);

    }

    public void waitUtilElementToBeInvisible(WebElement element){
        wait.until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    protected void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
