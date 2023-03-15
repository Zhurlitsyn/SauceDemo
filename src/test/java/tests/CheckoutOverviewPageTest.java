package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.CheckoutInformationPage.*;
import static pages.CheckoutOverviewPage.*;

public class CheckoutOverviewPageTest extends BaseTest {
    double totalProductsPrice, summaryTax, summaryTotal, total, calculatedTax;
    double scale = Math.pow(10, 2);
    public void roadToCheckoutOverviewPage() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCartList();
        productsPage.openCart();
        cartPage.checkout();
        checkoutPage.setLoginData(FIRST_NAME_INPUT, LAST_NAME_INPUT, POSTAL_CODE_INPUT);
    }

    @Test
    public void checkEqualPriceTax() {
        roadToCheckoutOverviewPage();
        List<WebElement> productList = driver.findElements(By.cssSelector(".inventory_item_price"));
        totalProductsPrice = 0;
        for (WebElement l : productList) {
            totalProductsPrice += checkoutOverviewPage.getForTotalProductPrice(l.getText());
        }
        calculatedTax = Math.ceil((totalProductsPrice/100*8)*scale)/scale;
        summaryTax = checkoutOverviewPage.getPriceTax(SUMMARY_TAX);
        summaryTotal = checkoutOverviewPage.getPriceTax(TOTAL_PRICE);
        total = checkoutOverviewPage.getPriceTax(TOTAL);

        assertTrue((totalProductsPrice == summaryTotal), "Wrong result");
        assertTrue((calculatedTax == summaryTax), "Wrong result");
        assertTrue((total == (summaryTax+summaryTotal)), "Wrong result");

    }
    @Test
    public void buttonFinishClick() {
        checkoutOverviewPage.buttonFinishClick();
        String str = productsPage.getTitle();
        assertEquals(str, "Checkout: Complete!", "Wrong Title");
    }
}
