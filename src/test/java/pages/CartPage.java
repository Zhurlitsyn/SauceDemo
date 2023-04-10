package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public static final String removeFromCartXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'cart_item')]//button";
    public static final String priceXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'cart_item')]//div[@class='inventory_item_price']";

    public static final By CHECKOUT_BUTTON = By.id("checkout");
    public static final By CONTINUE_SHOPPING = By.id("continue-shopping");
    public static final By LIST_PRODUCTS = By.cssSelector(".cart_item");
    public static final By CONTAINER_BADGE = By.xpath("//div[@class='shopping_cart_container']//span");
    public static final By LINK_DETAIL_NAME = By.xpath("//a[starts-with(@id,'item')]");
    public static final By DETAIL_NAME = By.xpath("//div[@class='inventory_details_name large_size']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/cart.html");
    }

    @Step("Click on checkout button")
    public void checkout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    @Step("Click on Continue shopping button")
    public void continueShopping() {
        driver.findElement(CONTINUE_SHOPPING).click();
    }

    @Step("Remove product from Cart")
    public void removeFromCartPage(String product) {
    By removeFromCartLocator = By.xpath(String.format(removeFromCartXpath, product));
    driver.findElement(removeFromCartLocator).click();
    }

    @Step("Getting number of products in Cart by badge")
    public Integer getNumberOnBadge() {
        return Integer.parseInt(driver.findElement(CONTAINER_BADGE).getText());
    }

    @Step("Getting size of product list")
    public Integer getNumberOfProducts() {
        return driver.findElements(LIST_PRODUCTS).size();
    }

    @Step("Getting product price")
    public String getPriceOfProduct(String product) {
        By priceLocator = By.xpath(String.format(priceXpath, product));
        return driver.findElement(priceLocator).getText();
    }

    @Step("Getting link detail product name")
    public String linkDetailProductName() {
        return driver.findElement(LINK_DETAIL_NAME).getText();
    }

    @Step("Click detail name link")
    public void linkDetailProductNameClick() {
        driver.findElement(LINK_DETAIL_NAME).click();
    }

    @Step("Getting detail product name")
    public String getDetailName() {
        return driver.findElement(DETAIL_NAME).getText();
    }


}
