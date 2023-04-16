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
import static pages.CheckoutInformationPage.*;
import static pages.CheckoutOverviewPage.*;
@Log4j2
public class CheckoutOverviewPageTest extends BaseTest {
    double totalProductsPrice, summaryTax, summaryTotal, total, calculatedTax;
    double scale = Math.pow(10, 2);
    @Step("Login main page, add products to cart, checkout info page opening")
    public void roadToCheckoutOverviewPage() {
        log.info("Path to Checkout page and det login data");
        loginPage.open();
        String login = System.getProperty("username", PropertyReader.getProperty("USERNAME"));
        String login2 = System.getProperty("password", PropertyReader.getProperty("PASSWORD"));
        this.loginPage.login(login, login2);
        //loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCartList();
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.setLoginData(FIRST_NAME_INPUT, LAST_NAME_INPUT, POSTAL_CODE_INPUT);
    }

    @Test(description = "Checking prices, total price, taxes")
    public void checkEqualPriceTax() {
        roadToCheckoutOverviewPage();
        log.info("Making list of Products");
        List<WebElement> productList = driver.findElements(By.cssSelector(".inventory_item_price"));
        totalProductsPrice = 0;
        for (WebElement l : productList) {
            totalProductsPrice += checkoutOverviewPage.getForTotalProductPrice(l.getText());
        }
        log.info("Calculating taxes, totals");
        calculatedTax = Math.ceil((totalProductsPrice/100*8)*scale)/scale;
        summaryTax = checkoutOverviewPage.getPriceTax(SUMMARY_TAX);
        summaryTotal = checkoutOverviewPage.getPriceTax(TOTAL_PRICE);
        total = checkoutOverviewPage.getPriceTax(TOTAL);

        assertTrue((totalProductsPrice == summaryTotal), "Wrong result");
        assertTrue((calculatedTax == summaryTax), "Wrong result");
        assertTrue((total == (summaryTax+summaryTotal)), "Wrong result");

    }
    @Test(description = "Finish button clicking")
    public void buttonFinishClick() {
        roadToCheckoutOverviewPage();
        checkoutOverviewPage.buttonFinishClick();
        String str = productsPage.getTitle();
        assertEquals(str, "Checkout: Complete!", "Wrong Title");
    }
}
