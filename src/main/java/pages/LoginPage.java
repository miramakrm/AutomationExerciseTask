package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    private final By loginErrorMessage =
            By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    public String getLoginErrorMessage() {
        return getText(loginErrorMessage);
    }
    private final By emailField =
            By.cssSelector("[data-qa='login-email']");

    private final By passwordField =
            By.cssSelector("[data-qa='login-password']");

    private final By loginBtn =
            By.cssSelector("[data-qa='login-button']");

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
}