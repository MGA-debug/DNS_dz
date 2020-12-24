package ru.dnsShop.freamwork.pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.dnsShop.freamwork.Products.Product;

import static org.junit.Assert.assertEquals;


public class ProductPage extends BasePage {
    Product product;
    int price;


    @FindBy(xpath = "//span[@class='product-card-price__current']")
    WebElement priceInPage;

    @FindBy(xpath = "//span[@class='product-card-price__current product-card-price__current_active']")
    WebElement priceWithInsurance;

    @FindBy(xpath = "//select[@class='form-control']/option[text()='2 года']")
    WebElement insuranceElement;

    @FindBy(xpath = "//button[text() = 'Купить']")
    WebElement buttonBuy;

    @FindBy(xpath = "//span[@class='cart-link__badge']")
    WebElement bucket;

    public ProductPage rememberThePrice(String s) {
        product = new Product(s);
        listProduct.add(product);
        price = Product.convertPrice(priceInPage);
        product.setPriceWithOutInsurance(price);

        return this;
    }

    public ProductPage checkChangePrice() {
        int timeOut = 10;
        while(timeOut > 0){
            if (price != Product.convertPrice(priceWithInsurance)){
                break;
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            timeOut--;
        }
        return this;
    }


    public ProductPage selectInsurance() {
        insuranceElement.click();
        return this;
    }


    public ProductPage clickBuy() {
        buttonBuy.click();
        return this;
    }

    public ProductPage findAndPickElementInInProductPage(String name) {
        inputSearchProduct.sendKeys("detroit");
        inputSearchProduct.sendKeys(Keys.ENTER);
        return this;
    }

    public BucketPage goToBucket() {
        bucket.click();


        return app.getBucketPage();
    }
}
