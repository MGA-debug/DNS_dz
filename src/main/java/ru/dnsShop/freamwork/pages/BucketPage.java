package ru.dnsShop.freamwork.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.dnsShop.freamwork.Products.Product;

import java.util.List;

public class BucketPage extends BasePage {


    @FindBy(xpath = "//div[@class='cart-items__products']/*//div[@class='cart-items__product-name']")
    static List<WebElement> bucketList;

    @FindBy(xpath = "//div[@class='additional-warranties-row cart-items__additionals-item']")
    List<WebElement> productWithInsurance;

    @FindBy(xpath = "//span/span[@class='restore-last-removed']")
    WebElement returnRemove;


    public BucketPage checkInsurance(String nameProduct, String month) {
        WebElement webElement;
        WebElement insurance;
        for (WebElement element : productWithInsurance) {
            webElement = element.findElement(By.xpath("./../..//a[@class='cart-items__product-name-link']"));
            if (webElement.getText().toLowerCase().contains(nameProduct)) {
                insurance = element.findElement(By.xpath(".//span[@class='base-ui-radio-button__icon base-ui-radio-button__icon_checked']"));
                if (insurance.getText().contains(month)) {
                    return this;
                }
                Assert.fail("Страховка на продукт не соотвествует требуемой");
            }
        }
        Assert.fail("Товара с таким именем не найдено");
        return this;
    }

    public BucketPage checkPriceInBucket() {
    WebElement elementInBucket;
    String name;

    for (WebElement element: bucketList) {
        name = element.getText().toLowerCase();
        elementInBucket = element.findElement(By.xpath("./../..//span[@class='price__current']"));
        for (Product product: listProduct) {
            if (name.contains(product.getTitle())){
                if (Product.convertPrice(elementInBucket) != product.getPriceWithOutInsurance())
                    Assert.fail("Цена в корзине не соответствует цене на странице продукта");
            }
        }
    }
    Assert.assertEquals("Ошибка в итоговой стоимости товаров",
            totalCostProductInBucket(),Product.totalCostProductList(listProduct));

    return this;
    }

    public BucketPage removeElementOfBucketAndCheck(String productName) {
        int costBeforeRemoveProduct = totalCostProductInBucket();
        WebElement deleteButton;

        for (WebElement element : bucketList) {
            if (element.getText().toLowerCase().contains(productName)) {
                deleteButton = element.findElement(By.xpath("./../..//button[text()='Удалить']"));
                deleteButton.click();

                waitUtilElementToBeInvisible(element);
            }
        }

        for (int i = 0;i < listProduct.size(); i++) {
            if (listProduct.get(i).getTitle().contains(productName)) {
                removedProduct.push(listProduct.get(i));
                listProduct.remove(listProduct.get(i));
            }
        }

        int costAfterRemoveProduct = totalCostProductInBucket();

        if (costAfterRemoveProduct == costBeforeRemoveProduct)
            Assert.fail("Товар не был удален из корзины");

        return this;
    }

    public static int totalCostProductInBucket() {
        int totalCost = 0;
        WebElement element;
        for (WebElement product : bucketList) {
            element = product.findElement(By.xpath("./../..//span[@class='price__current']"));
            totalCost += Product.convertPrice(element);
        }

        return totalCost;
    }


    public BucketPage plusProduct(String productName, int quantityAdd) {
        WebElement plus;
        int timeOut = 5;

        for (WebElement element : bucketList) {

            if (element.getText().toLowerCase().contains(productName)) {
                plus = element.findElement(By.xpath("./../..//button[@data-commerce-action='CART_ADD']"));
                scrollWithOffset(plus,-1000,-1000);
                int quantity = 0;
                while (timeOut > 0){
                     quantity = Product.convertValue(element.findElement(By.xpath("./../..//input")));
                    plus.click();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(quantity == quantityAdd) break;

                    timeOut--;
                }

            }
        }
        return this;
    }


    public BucketPage returnRemoveProduct() {
        Product product;
        int sum = totalCostProductInBucket();
        int numberOfGoods = bucketList.size();
        int timeOut = 10;
        scrollWithOffset(returnRemove, -200, -200);
        returnRemove.click();

        while (timeOut > 0){
            if (numberOfGoods == bucketList.size()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            timeOut--;
        }

        product = removedProduct.pop();
        listProduct.add(product);


        Assert.assertEquals("После возврата удаленного товара цена не изменилась",
                totalCostProductInBucket(),sum+product.getPriceWithOutInsurance());
        return this;
    }
}
