package ru.dnsShop.freamwork.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//div[@data-id='catalog-item']")
    List<WebElement> listProduct;

    public ProductPage selectAndFindProduct(String nameProduct) {
        WebElement titleProduct;
        for (WebElement menuItem : listProduct) {
            titleProduct = menuItem.findElement(By.xpath(".//div[@class='product-info__title-link']/a"));
            if (titleProduct.getText().toLowerCase().contains(nameProduct)) {

                titleProduct.click();
                return app.getProductPage();
            }
        }
        Assert.fail("Товар:" + nameProduct + "' не был найден на странице поиска!");
        return app.getProductPage();
    }

}
