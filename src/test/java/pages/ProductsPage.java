package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProductsPage extends BasePage{
    String addToCartXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'inventory_item')]//button";
    public static final List<String> productsList = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Fleece Jacket");
    public static final List<String> productsPriceList = List.of("$29.99", "$9.99", "$49.99");

    public static final By CART = By.id("shopping_cart_container");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }


    public void addToCart(String product) {
        By addToCartLocator = By.xpath(String.format(addToCartXpath, product));
        driver.findElement(addToCartLocator).click();
    }

    public void addToCartList() {
        for (String l : productsList) {
            addToCart(l);
        }
    }


    public void removeFromCartCatalog(String product) {
        addToCart(product);
        By addToCartLocator = By.xpath(String.format(addToCartXpath, product));
        driver.findElement(addToCartLocator).click();
    }

    public void openCart() {
        driver.findElement(CART).click();
    }
}
