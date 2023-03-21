package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.CartPage.removeFromCartXpath;
import static pages.ProductsPage.productsList;
import static pages.ProductsPage.productsPriceList;

public class CartPageTest extends BaseTest {

    String checkTitle; //
    @Step("Login main page, add products to cart, cart page opening")
    public void roadToCartPage() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
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
        roadToCartPage();
        int numberOnBadge = cartPage.getNumberOnBadge();
        int sizeProduct = cartPage.getNumberOfProducts();
        assertTrue((numberOnBadge == sizeProduct), "Amount not Equals");
    }

    @Test(description = "Check if products remove from cart")
    public void removeProductCartPage() {
        roadToCartPage();
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
        String productName = cartPage.linkDetailProductName();
        cartPage.linkDetailProductNameClick();
        String detailName = cartPage.getDetailName();
        assertEquals(productName, detailName, "Names of product are DIFFERENT");
    }

    @Test(description = "Checking prices of products")
    public void matchPricesOfProducts() {
        roadToCartPage();
        String getProductPrice;
        for (int i = 0; i < productsList.size(); i++) {
            getProductPrice = cartPage.getPriceOfProduct(productsList.get(i));
            assertEquals(getProductPrice, productsPriceList.get(i), "Some price is DIFFERENT");
        }
    }

}
