package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private final By signupLoginBtn =
            By.xpath("//a[contains(text(),'Signup / Login')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickSignupLogin() {
        closeAdsIfPresent();
        WebElement element = waitUtils.waitForVisibility(signupLoginBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}