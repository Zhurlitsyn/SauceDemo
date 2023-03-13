package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD); //

        String checkStr = productsPage.getTitle();
        assertEquals(checkStr, "Products", "Login is unsuccessful");
    }

    @Test
    public void userNameIsRequired() {
        loginPage.open();
        loginPage.login("", "");
        String checkStr = loginPage.getErrorMessage();
        assertEquals(checkStr, "Epic sadface: Username is required", "Wrong error message");

    }

    @Test
    public void passwordIsRequired() {
        loginPage.open();
        loginPage.login("standard_user", "");
        String checkStr = loginPage.getErrorMessage();
        assertEquals(checkStr, "Epic sadface: Password is required", "Wrong error message");
    }

    @Test
    public void anyUserDontMatch() {
        loginPage.open();
        loginPage.login("any_user", "any_password");
        String checkStr = loginPage.getErrorMessage();
        assertEquals(checkStr,
                "Epic sadface: Username and password do not match any user in this service",
                "Wrong error message");
    }

    @Test
    public void lockedUserLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        String checkStr = loginPage.getErrorMessage();
        assertEquals(checkStr,
                "Epic sadface: Sorry, this user has been locked out.",
                "Wrong error message");
    }

}
