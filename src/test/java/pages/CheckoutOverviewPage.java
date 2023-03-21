package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {
    public static final By SUMMARY_TAX = By.cssSelector(".summary_tax_label");
    public static final By TOTAL_PRICE = By.cssSelector(".summary_subtotal_label");
    public static final By TOTAL = By.cssSelector(".summary_total_label");
    public static final By FINISH_BUTTON = By.id("finish");
    public static final String productPriceXpath = "//*[text()='%s']/ancestor::*[contains(@class, 'cart_item')]//div[@class='inventory_item_price']";


    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Getting Tax")
    public Double getPriceTax(By by) {
        double price = 0;
        String str = driver.findElement(by).getText();
        price = Double.parseDouble(str.substring(str.indexOf("$") + 1, str.length()));
        return price;
    }
    @Step("Getting total products price")
    public Double getForTotalProductPrice(String str) {
        return Double.parseDouble(str.substring(1));

    }
    @Step("Finish button clicking")
    public void buttonFinishClick() {
        driver.findElement(FINISH_BUTTON).click();
    }

}
