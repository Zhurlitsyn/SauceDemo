package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProblemUserTest extends BaseTest {
    public ProblemUserTest() {
    }

    @Test(description = "Check if user successful login parameter 'user' use"
    )
    public void successfulLogin() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        String checkStr = productsPage.getTitle();
        Assert.assertEquals(checkStr, "Products", "Login is unsuccessful");
    }

}