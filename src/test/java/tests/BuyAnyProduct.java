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


 /*       driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Select sortGoods = new Select(driver
                .findElement(By.xpath("//select[@data-test='product_sort_container']")));
        sortGoods.selectByValue("hilo");
        driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']")).click();
        driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Jacket");
        driver.findElement(By.id("last-name")).sendKeys("Mascout");
        driver.findElement(By.id("postal-code")).sendKeys("85749");
        driver.findElement(By.id("continue")).click();
        String checkProductName = driver
                .findElement(By.xpath("//div[@class='inventory_item_name']"))
                .getText();
        assertEquals(checkProductName, "Sauce Labs Fleece Jacket", "Other product name");
        String checkPrice = driver
                .findElement(By.xpath("//div[@class='inventory_item_price']"))
                .getText();
        assertEquals(checkPrice, "$49.99", "Other price");
        driver.findElement(By.id("finish")).click();
        String result = driver.findElement(By.cssSelector(".title")).getText();
        assertEquals(result, "Checkout: Complete!", "Other title");*/

    }


}
