package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {
    String addToCartXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'inventory_item')]//button";
    public static final List<String> productsList = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Fleece Jacket");
    public static final List<String> productsPriceList = List.of("$29.99", "$9.99", "$49.99");

    public static final By CART = By.id("shopping_cart_container");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add product to cart by '{product}'")
    public void addToCart(String product) {
        By addToCartLocator = By.xpath(String.format(addToCartXpath, product));
        driver.findElement(addToCartLocator).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.textToBe(TITLE, "Products"));
    }

    @Step("Create list of products from page")
    public void addToCartList() {
        for (String l : productsList) {
            addToCart(l);
        }
    }
    @Step("Remove from cart '{product}'")
    public void removeFromCartCatalog(String product) {
        By addToCartLocator = By.xpath(String.format(addToCartXpath, product));
        driver.findElement(addToCartLocator).click();
    }

    @Step("Open Cart Page by click")
    public void openCart() {
        driver.findElement(CART).click();
    }
}
