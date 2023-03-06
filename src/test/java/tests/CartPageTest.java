package tests;

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

    String checkTitle;

    public void roadToCartPage() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCartList();
        productsPage.openCart();
    }

    @Test
    public void checkTitleCartPage() {
        roadToCartPage();
        checkTitle = productsPage.getTitle();
        assertEquals(checkTitle, "Your Cart", "Wrong page title");
    }

    @Test
    public void checkAmountProducts() {
        roadToCartPage();
        int numberOnBadge = cartPage.getNumberOnBadge();
        int sizeProduct = cartPage.getNumberOfProducts();
        assertTrue((numberOnBadge == sizeProduct), "Amount not Equals");
    }

    @Test
    public void removeProductCartPage() {
        roadToCartPage();
        cartPage.removeFromCartPage(productsList.get(0));
        cartPage.removeFromCartPage(productsList.get(1));
        String checkProductName = driver
                .findElement(By.xpath("//div[@class='inventory_item_name']"))
                .getText();
        assertEquals(checkProductName, productsList.get(2), "Wrong Product Name");
    }

    @Test
    public void checkoutButtonClick() {
        roadToCartPage();
        cartPage.checkout();
        checkTitle = productsPage.getTitle();
        assertEquals(checkTitle, "Checkout: Your Information", "Wrong page title");
    }

    @Test
    public void continueButtonClick() {
        roadToCartPage();
        cartPage.continueShopping();
        checkTitle = productsPage.getTitle();
        assertEquals(checkTitle, "Products", "Wrong page title");
    }

    @Test
    public void linkDetailsProduct() {
        roadToCartPage();
        WebElement productNameCheck = driver.findElement(By.xpath("//a[starts-with(@id,'item')]"));
        String productName = productNameCheck.getText();
        productNameCheck.click();

        String detailName = driver
                .findElement(By.xpath("//div[@class='inventory_details_name large_size']"))
                .getText();
        assertTrue(productName.equals(detailName), "Names of product are DIFFERENT");
    }

    @Test
    public void matchPricesOfProducts() {
        roadToCartPage();
        String getProductPrice;
        for (int i = 0; i < productsList.size(); i++) {
            getProductPrice = cartPage.getPriceOfProduct(productsList.get(i));
            assertTrue(getProductPrice.equals(productsPriceList.get(i)), "Some price is DIFFERENT");
        }
    }

}
