package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProblemUserTest extends BaseTest {
    public ProblemUserTest() {
    }

    @Test(
            description = "Check if user successful login"
    )
    public void successfulLogin() {
        loginPage.open();
        loginPage.login(System.getProperty("user"), "secret_sauce");
        String checkStr = productsPage.getTitle();
        Assert.assertEquals(checkStr, "Products", "Login is unsuccessful");
    }

}