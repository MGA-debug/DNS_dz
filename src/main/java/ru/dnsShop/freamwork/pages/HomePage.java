package ru.dnsShop.freamwork.pages;

import org.openqa.selenium.Keys;


public class HomePage extends BasePage {

    public SearchResultsPage inputInSearchString(String product) {
        inputSearchProduct.sendKeys("playstation");
        inputSearchProduct.sendKeys(Keys.ENTER);

        return app.getSearchResultsPage();
    }
}

