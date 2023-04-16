package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class ProblemUserTest extends BaseTest {
    public ProblemUserTest() {
    }

    @Test(description = "Check if user successful login parameter 'user' use"
    )
    public void successfulLogin() {
        loginPage.open();
        String login = System.getProperty("username", PropertyReader.getProperty("USERNAME"));
        String login2 = System.getProperty("password", PropertyReader.getProperty("PASSWORD"));
        this.loginPage.login(login, login2);
        //loginPage.login(USERNAME, PASSWORD);
        String checkStr = productsPage.getTitle();
        Assert.assertEquals(checkStr, "Products", "Login is unsuccessful");
    }

}