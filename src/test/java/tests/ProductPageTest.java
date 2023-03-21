package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductPageTest extends BaseTest {

    @Test(description = "Check if any products can buy")
    public void buyAnyProduct() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.isPageOpened();
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        productsPage.openCart();
        cartPage.checkout();
        String str = loginPage.getTitle();
        assertEquals(str, "Checkout: Your Information", "Wrong title");
    }

    @Test(description = "Check if any product can remove")
    public void removeProductFromCart() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.isPageOpened();
        productsPage.addToCartList();
        productsPage.removeFromCartCatalog("Sauce Labs Fleece Jacket");
        int num = cartPage.getNumberOnBadge();
        assertEquals(num, 2, "Wrong amount of products");
    }


}
