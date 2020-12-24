package ru.dnsShop.freamwork.Products;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Product {

    String title;
    int priceWithOutInsurance;


    public Product(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public int getPriceWithOutInsurance() {
        return priceWithOutInsurance;
    }

    public static int convertPrice(WebElement w) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(w.getText().replaceAll("\\D", ""));
    }

    public static int convertValue(WebElement w) {
        return Integer.parseInt(w.getAttribute("value"));
    }


    public void setPriceWithOutInsurance(int priceWithOutInsurance) {
        this.priceWithOutInsurance = priceWithOutInsurance;
    }

    public static int totalCostProductList(List<Product> list) {
        int totalCost = 0;
        for (Product p: list)
            totalCost += p.getPriceWithOutInsurance();

        return totalCost;
    }
}
