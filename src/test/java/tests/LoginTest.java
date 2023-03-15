package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    public LoginTest() {
    }

    @Test(
            description = "Check if user successful login"
    )
    public void successfulLogin() {
        this.loginPage.open();
        this.loginPage.login("standard_user", "secret_sauce");
        String checkStr = this.productsPage.getTitle();
        Assert.assertEquals(checkStr, "Products", "Login is unsuccessful");
    }

    @DataProvider(
            name = "Input data for negative login tests"
    )
    public Object[][] getDataForLogin() {
        return new Object[][]{{"", "", "Epic sadface: Username is required"}, {"standard_user", "", "Epic sadface: Password is required"}, {"test", "pass", "Epic sadface: Username and password do not match any user in this service"}, {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}};
    }

    @Test(
            description = "Negative Login tests",
            dataProvider = "Input data for negative login tests"
    )
    public void negativeLogin(String username, String password, String expectedError) {
        this.loginPage.open();
        this.loginPage.login(username, password);
        String checkStr = this.loginPage.getErrorMessage();
        Assert.assertEquals(checkStr, expectedError, "Wrong Message");
    }

    @Test(
            description = "Username is required"
    )
    public void userNameIsRequired() {
        this.loginPage.open();
        this.loginPage.login("", "");
        String checkStr = this.loginPage.getErrorMessage();
        Assert.assertEquals(checkStr, "Epic sadface: Username is required", "Wrong error message");
    }

    @Test
    public void passwordIsRequired() {
        this.loginPage.open();
        this.loginPage.login("standard_user", "");
        String checkStr = this.loginPage.getErrorMessage();
        Assert.assertEquals(checkStr, "Epic sadface: Password is required", "Wrong error message");
    }
}