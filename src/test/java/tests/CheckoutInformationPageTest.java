package tests;

import org.testng.annotations.Test;

import static pages.CheckoutInformationPage.*;

import static org.testng.Assert.assertEquals;
import static pages.LoginPage.*;

public class CheckoutInformationPageTest extends BaseTest {
    public void roadToCheckoutInfoPage() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCartList();
        productsPage.openCart();
        cartPage.checkout();
    }

    @Test
    public void errorEmptyFirstName() {
        roadToCheckoutInfoPage();
        checkoutPage.setEmptyFirstName();
        String error = driver.findElement(ERROR_MESSAGE).getText();
        assertEquals(error, "Error: First Name is required", "Wrong error message");
    }

    @Test
    public void errorEmptyLastName() {
        roadToCheckoutInfoPage();
        checkoutPage.setEmptyLastName();
        String error = driver.findElement(ERROR_MESSAGE).getText();
        assertEquals(error, "Error: Last Name is required", "Wrong error message");
    }

    @Test
    public void errorEmptyPostalCode() {
        roadToCheckoutInfoPage();
        checkoutPage.setEmptyPostalCode();
        String error = driver.findElement(ERROR_MESSAGE).getText();
        assertEquals(error, "Error: Postal Code is required", "Wrong error message");
    }

    @Test
    public void successCheckOutPage() {
        roadToCheckoutInfoPage();
        checkoutPage.setLoginData(FIRST_NAME_INPUT, LAST_NAME_INPUT, POSTAL_CODE_INPUT);
        String getTitle = productsPage.getTitle();
        assertEquals(getTitle, "Checkout: Overview", "Wrong page title");
    }

    @Test
    public void cancelCheckOutPage() {
        roadToCheckoutInfoPage();
        checkoutPage.cancelButtonClick();
        String getTitle = productsPage.getTitle();
        assertEquals(getTitle, "Your Cart", "Wrong page title");
    }

}
