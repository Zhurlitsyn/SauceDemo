package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class CheckoutInformationPage extends BasePage {

    public static final By FIRST_NAME = By.id("first-name");
    public static final By LAST_NAME = By.id("last-name");
    public static final By POSTAL_CODE = By.id("postal-code");
    public static final By CONTINUE_BUTTON = By.id("continue");
    public static final By CANCEL_BUTTON = By.id("cancel");
    public static final String FIRST_NAME_INPUT = "Silvek";
    public static final String LAST_NAME_INPUT = "Mrochek";
    public static final String POSTAL_CODE_INPUT = "89752";

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void setLoginData(String firstname, String lastname, String postalcode) {
        driver.findElement(FIRST_NAME).sendKeys(firstname);
        driver.findElement(LAST_NAME).sendKeys(lastname);
        driver.findElement(POSTAL_CODE).sendKeys(postalcode);
        driver.findElement(CONTINUE_BUTTON).click();
        waitForPageLoaded();
    }

    public void setEmptyFirstName() {
        driver.findElement(FIRST_NAME).sendKeys("");
        driver.findElement(LAST_NAME).sendKeys(LAST_NAME_INPUT);
        driver.findElement(POSTAL_CODE).sendKeys(POSTAL_CODE_INPUT);
        driver.findElement(CONTINUE_BUTTON).click();
    }
    public void setEmptyLastName() {
        driver.findElement(FIRST_NAME).sendKeys(FIRST_NAME_INPUT);
        driver.findElement(LAST_NAME).sendKeys("");
        driver.findElement(POSTAL_CODE).sendKeys(POSTAL_CODE_INPUT);
        driver.findElement(CONTINUE_BUTTON).click();
    }
    public void setEmptyPostalCode() {
        driver.findElement(FIRST_NAME).sendKeys(FIRST_NAME_INPUT);
        driver.findElement(LAST_NAME).sendKeys(LAST_NAME_INPUT);
        driver.findElement(POSTAL_CODE).sendKeys("");
        driver.findElement(CONTINUE_BUTTON).click();
    }
    public void cancelButtonClick() {
        driver.findElement(CANCEL_BUTTON).click();
    }

}

