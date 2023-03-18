//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CartPage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import pages.ProductsPage;

@Listeners({TestListener.class})
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CheckoutInformationPage checkoutPage;
    CartPage cartPage;
    CheckoutOverviewPage checkoutOverviewPage;
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";

    public BaseTest() {
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments(new String[]{"--remote-allow-origins=*"});
            options.addArguments(new String[]{"--headless"});
            options.addArguments(new String[]{"--start-maximized"});
            this.driver = new ChromeDriver(options);
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
        }

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.loginPage = new LoginPage(this.driver);
        this.productsPage = new ProductsPage(this.driver);
        this.cartPage = new CartPage(this.driver);
        this.checkoutPage = new CheckoutInformationPage(this.driver);
        this.checkoutOverviewPage = new CheckoutOverviewPage(this.driver);
    }

    @AfterMethod(
            alwaysRun = true
    )
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

    }
}
