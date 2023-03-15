package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BuyAnyProductTest extends BaseTest {

    @Test
    public void buyAnyProduct() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.isPageOpened();
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        productsPage.openCart();
        cartPage.checkout();
    }


}
