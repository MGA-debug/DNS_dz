package ru.dnsShop.freamwork.managers;

import ru.dnsShop.freamwork.pages.BucketPage;
import ru.dnsShop.freamwork.pages.HomePage;
import ru.dnsShop.freamwork.pages.ProductPage;
import ru.dnsShop.freamwork.pages.SearchResultsPage;

public class ManagerPages {

    private static ManagerPages managerPages;

    HomePage homePage;

    SearchResultsPage searchResultsPage;

    ProductPage productPage;

    BucketPage bucketPage;

    private ManagerPages() {
    }

    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public SearchResultsPage getSearchResultsPage() {
        if (searchResultsPage == null) {
            searchResultsPage = new SearchResultsPage();
        }
        return searchResultsPage;
    }

    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    public BucketPage getBucketPage() {
        if (bucketPage == null) {
            bucketPage = new BucketPage();
        }
        return bucketPage;
    }




}
