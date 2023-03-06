package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BuyAnyProduct extends BaseTest {

    @Test
    public void buyAnyProduct() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        productsPage.openCart();
        cartPage.checkout();
    }


}
