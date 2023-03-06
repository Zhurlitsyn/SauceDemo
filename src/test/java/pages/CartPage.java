package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public static final String removeFromCartXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'cart_item')]//button";
    String priceXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'cart_item')]//div[@class='inventory_item_price']";

    public static final By CHECKOUT_BUTTON = By.id("checkout");
    public static final By CONTINUE_SHOPPING = By.id("continue-shopping");
    public static final By LIST_PRODUCTS = By.cssSelector(".cart_item");
    public static final By containerBadge = By.xpath("//div[@class='shopping_cart_container']//span");

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public void open() {
        driver.get(BASE_URL + "/cart.html");
    }

    public void checkout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void continueShopping() {
        driver.findElement(CONTINUE_SHOPPING).click();
    }

    public void removeFromCartPage(String product) {
    By removeFromCartLocator = By.xpath(String.format(removeFromCartXpath, product));
    driver.findElement(removeFromCartLocator).click();
    }
    public Integer getNumberOnBadge() {
        return Integer.parseInt(driver.findElement(containerBadge).getText());
    }
    public Integer getNumberOfProducts() {
        return driver.findElements(LIST_PRODUCTS).size();
    }

    public String getPriceOfProduct(String product) {
        By priceLocator = By.xpath(String.format(priceXpath, product));
        return driver.findElement(priceLocator).getText();
    }


}
