package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    public LoginTest() {
    }

    @Test(description = "Check if user successful login")
    public void successfulLogin() {
        this.loginPage.open();
        this.loginPage.login(USERNAME, PASSWORD);
        String checkStr = this.productsPage.getTitle();
        Assert.assertEquals(checkStr, "Products", "Login is unsuccessful");
    }
    @Test(description = "Check if user successful login")
    public void successfulLoginUsingSecrets() {
        this.loginPage.open();
        String login = System.getProperty("username", PropertyReader.getProperty("USERNAME"));
        String login2 = System.getProperty("password", PropertyReader.getProperty("PASSWORD"));
        this.loginPage.login(login, login2);
        String checkStr = this.productsPage.getTitle();
        Assert.assertEquals(checkStr, "Products", "Login is unsuccessful");
    }

    @DataProvider(name = "Input data for negative login tests")
    public Object[][] getDataForLogin() {
        return new Object[][]{{"", "", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "pass", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}};
    }

    @Test(
            description = "Negative Login tests",
            dataProvider = "Input data for negative login tests"
    )
    public void negativeLogin(String username, String password, String expectedError) {
        loginPage.open();
        loginPage.login(username, password);
        String checkStr = loginPage.getErrorMessage();
        Assert.assertEquals(checkStr, expectedError, "Wrong Message");
    }

    @Test(description = "Username is required")
    public void userNameIsRequired() {
        loginPage.open();
        loginPage.login("", "");
        String checkStr = loginPage.getErrorMessage();
        Assert.assertEquals(checkStr, "Epic sadface: Username is required", "Wrong error message");
    }

    @Test(description = "Password is required")
    public void passwordIsRequired() {
        loginPage.open();
        loginPage.login("standard_user", "");
        String checkStr = loginPage.getErrorMessage();
        Assert.assertEquals(checkStr, "Epic sadface: Password is required", "Wrong error message");
    }
}