package tests;

import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static pages.CheckoutInformationPage.*;

import static org.testng.Assert.assertEquals;
import static pages.LoginPage.*;

public class CheckoutInformationPageTest extends BaseTest {
    @Step("Login main page, add products to cart, checkout page opening")
    public void roadToCheckoutInfoPage() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        productsPage.addToCartList();
        productsPage.openCart();
        cartPage.checkout();
    }

    @Test(description = "Firstname is required")
    public void errorEmptyFirstName() {
        roadToCheckoutInfoPage();
        checkoutPage.setLoginData("", LAST_NAME_INPUT, POSTAL_CODE_INPUT);
        String error = driver.findElement(ERROR_MESSAGE).getText();
        assertEquals(error, "Error: First Name is required", "Wrong error message");
    }

    @Test(description = "Lastname is required")
    public void errorEmptyLastName() {
        roadToCheckoutInfoPage();
        checkoutPage.setLoginData(FIRST_NAME_INPUT, "", POSTAL_CODE_INPUT);
        String error = driver.findElement(ERROR_MESSAGE).getText();
        assertEquals(error, "Error: Last Name is required", "Wrong error message");
    }

    @Test(description = "PostalCode is required")
    public void errorEmptyPostalCode() {
        roadToCheckoutInfoPage();
        checkoutPage.setLoginData(FIRST_NAME_INPUT, LAST_NAME_INPUT, "");
        String error = driver.findElement(ERROR_MESSAGE).getText();
        assertEquals(error, "Error: Postal Code is required", "Wrong error message");
    }

    @Test(description = "Check if user successful checkout")
    public void successCheckOutPage() {
        roadToCheckoutInfoPage();
        checkoutPage.setLoginData(FIRST_NAME_INPUT, LAST_NAME_INPUT, POSTAL_CODE_INPUT);
        String getTitle = productsPage.getTitle();
        assertEquals(getTitle, "Checkout: Overview", "Wrong page title");
    }

    @Test(description = "Cancel button on checkout page clicking")
    public void cancelCheckOutPage() {
        roadToCheckoutInfoPage();
        checkoutPage.cancelButtonClick();
        String getTitle = productsPage.getTitle();
        assertEquals(getTitle, "Your Cart", "Wrong page title");
    }

}
