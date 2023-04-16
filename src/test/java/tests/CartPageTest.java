package tests;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.PropertyReader;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.CartPage.removeFromCartXpath;
import static pages.ProductsPage.productsList;
import static pages.ProductsPage.productsPriceList;
@Log4j2
public class CartPageTest extends BaseTest {

    String checkTitle; //
    @Step("Login main page, add products to cart, cart page opening")
    public void roadToCartPage() {
        log.info("Login main page, add products to cart, cart page opening");
        loginPage.open();
        String login = System.getProperty("username", PropertyReader.getProperty("USERNAME"));
        String login2 = System.getProperty("password", PropertyReader.getProperty("PASSWORD"));
        this.loginPage.login(login, login2);
        //loginPage.login(USERNAME, PASSWORD);
        log.info("Trying add products to Cart");
        productsPage.addToCartList();
        productsPage.openCart();
    }

    @Test(description = "Check if title match")
    public void checkTitleCartPage() {
        roadToCartPage();
        checkTitle = productsPage.getTitle();
        assertEquals(checkTitle, "Your Cart", "Wrong page title");
    }

    @Test(description = "Check product list size vs number on badge")
    public void checkAmountProducts() {
        log.info("Check product list size vs number on badge");
        roadToCartPage();
        int numberOnBadge = cartPage.getNumberOnBadge();
        int sizeProduct = cartPage.getNumberOfProducts();
        assertTrue((numberOnBadge == sizeProduct), "Amount not Equals");
    }

    @Test(description = "Check if products remove from cart")
    public void removeProductCartPage() {
        log.info("Check if products remove from cart");
        roadToCartPage();
        log.info("Trying to delete product from Cart");
        cartPage.removeFromCartPage(productsList.get(0));
        cartPage.removeFromCartPage(productsList.get(1));
        String checkProductName = driver
                .findElement(By.xpath("//div[@class='inventory_item_name']"))
                .getText();
        assertEquals(checkProductName, productsList.get(2), "Wrong Product Name");
    }

    @Test(description = "Check title on 'Checkout' button click")
    public void checkoutButtonClick() {
        roadToCartPage();
        cartPage.checkout();
        checkTitle = productsPage.getTitle();
        assertEquals(checkTitle, "Checkout: Your Information", "Wrong page title");
    }

    @Test(description = "Check title on 'Continue' button click")
    public void continueButtonClick() {
        roadToCartPage();
        cartPage.continueShopping();
        checkTitle = productsPage.getTitle();
        assertEquals(checkTitle, "Products", "Wrong page title");
    }

    @Test(description = "Check detail product link")
    public void linkDetailsProduct() {
        roadToCartPage();
        log.info("Get product name on detail page");
        String productName = cartPage.linkDetailProductName();
        cartPage.linkDetailProductNameClick();
        log.info("Get product name on cart page");
        String detailName = cartPage.getDetailName();
        assertEquals(productName, detailName, "Names of product are DIFFERENT");
    }

    @Test(description = "Checking prices of products")
    public void matchPricesOfProducts() {
        log.info("Checking prices of products");
        roadToCartPage();
        String getProductPrice;
        for (int i = 0; i < productsList.size(); i++) {
            log.info("Getting price of product and checking");
            getProductPrice = cartPage.getPriceOfProduct(productsList.get(i));
            assertEquals(getProductPrice, productsPriceList.get(i), "Some price is DIFFERENT");
        }
    }

}
