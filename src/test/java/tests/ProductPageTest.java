package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;
@Log4j2
public class ProductPageTest extends BaseTest {

    @Test(description = "Check if any products can buy")
    public void buyAnyProduct() {
        loginPage.open();
        String login = System.getProperty("username", PropertyReader.getProperty("USERNAME"));
        String login2 = System.getProperty("password", PropertyReader.getProperty("PASSWORD"));
        this.loginPage.login(login, login2);
        //loginPage.login(USERNAME, PASSWORD);
        productsPage.isPageOpened();
        log.info("Add product to Cart");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        productsPage.openCart();
        cartPage.checkout();
        String str = loginPage.getTitle();
        assertEquals(str, "Checkout: Your Information", "Wrong title");
    }

    @Test(description = "Check if any product can remove")
    public void removeProductFromCart() {
        loginPage.open();
        String login = System.getProperty("username", PropertyReader.getProperty("USERNAME"));
        String login2 = System.getProperty("password", PropertyReader.getProperty("PASSWORD"));
        this.loginPage.login(login, login2);
        //loginPage.login(USERNAME, PASSWORD);
        productsPage.isPageOpened();
        log.info("Add products to Cart");
        productsPage.addToCartList();
        productsPage.removeFromCartCatalog("Sauce Labs Fleece Jacket");
        int num = cartPage.getNumberOnBadge();
        assertEquals(num, 2, "Wrong amount of products");
    }


}
