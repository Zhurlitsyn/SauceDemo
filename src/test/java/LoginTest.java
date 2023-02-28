import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String checkStr = driver
                .findElement(By.xpath("//span[@class='title']")).getText();
        assertEquals(checkStr, "PRODUCTS", "Login is unsuccessful");

    }
    @Test
    public void userNameIsRequired() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("login-button")).click();
        String checkStr = driver
                .findElement(By.cssSelector("[data-test=error]"))
                .getText();
        assertEquals(checkStr, "Epic sadface: Username is required", "Wrong error message");

        driver.findElement(By.xpath("//id='login_button'"));
        driver.findElement(By.xpath("//input[text()='Login']"));
        driver.findElement(By.xpath("//*[contains(@class,'btn')]"));
        driver.findElement(By.xpath("//class[contains(text(),'submit')]"));
        driver.findElement(By.xpath("//*[text()='password']//ancestor::div[@class='login_box']//input"));
        driver.findElement(By.xpath("//div[@class='login-box']//descendant::input[@id='login-button']"));
        driver.findElement(By.xpath("//div[@class='login-box']//following::input"));

    }
    @Test
    public void passwordIsRequired() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        String checkStr = driver
                .findElement(By.cssSelector("[data-test=error]"))
                .getText();
        assertEquals(checkStr, "Epic sadface: Password is required", "Wrong error message");
    }

    @Test
    public void anyUserDontMatch() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("any_user");
        driver.findElement(By.id("password")).sendKeys("any_password");
        driver.findElement(By.id("login-button")).click();
        String checkStr = driver
                .findElement(By.cssSelector("[data-test=error]"))
                .getText();
        assertEquals(checkStr,
                "Epic sadface: Username and password do not match any user in this service",
                "Wrong error message");
    }

    @Test
    public void lockedUserLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String checkStr = driver
                .findElement(By.cssSelector("[data-test=error]"))
                .getText();
        assertEquals(checkStr,
                "Epic sadface: Sorry, this user has been locked out.",
                "Wrong error message");
    }

}
