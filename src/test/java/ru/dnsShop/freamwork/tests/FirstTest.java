package ru.dnsShop.freamwork.tests;


import org.junit.Test;

import static ru.dnsShop.freamwork.util.ProductConstant.*;
import static ru.dnsShop.freamwork.util.ProductInsurance.INSURANCE_24_MONTH;

public class FirstTest extends BaseTest {


    @Test
    public void dnsTest() {
        app.getHomePage()
                .inputInSearchString(PLAYSTATION)
                .selectAndFindProduct(PLAYSTATION_4_SLIM_BLACK)
                .rememberThePrice(PLAYSTATION)
                .selectInsurance()
                .checkChangePrice()
                .clickBuy()
                .findAndPickElementInInProductPage(GAME_DETROIT)
                .rememberThePrice(GAME_DETROIT)
                .clickBuy()
                .goToBucket()
                .checkInsurance(PLAYSTATION,INSURANCE_24_MONTH)
                .checkPriceInBucket()
                .removeElementOfBucketAndCheck(GAME_DETROIT)
                .plusProduct(PLAYSTATION,2)
                .returnRemoveProduct();
    }
}
