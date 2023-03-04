package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

abstract class BasePage {
    public static final By TITLE = By.cssSelector(".title");
    WebDriver driver;
    public static final String BASE_URL = "https://www.saucedemo.com";
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
    BasePage(WebDriver driver) {
        this.driver = driver;
    }


}
